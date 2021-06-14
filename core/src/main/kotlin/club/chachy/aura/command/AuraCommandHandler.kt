package club.chachy.aura.command

import club.chachy.aura.command.args.ArgumentsContainer
import club.chachy.aura.command.data.env.EnvironmentData
import club.chachy.aura.command.data.executor.CommandContext
import club.chachy.aura.command.data.executor.data.Channel
import club.chachy.aura.command.factory.PrefixFactory
import club.chachy.aura.command.serialization.SerializationContext
import club.chachy.aura.command.serialization.SerializationFactory
import club.chachy.utils.embed
import club.chachy.utils.separator
import com.github.fcannizzaro.material.Colors
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User
import java.time.Instant

class AuraCommandHandler(
    private val serializationFactory: SerializationFactory,
    private val prefixFactory: PrefixFactory
) : CommandHandler(ArrayList()) {
    override fun handle(message: Message, author: User, member: Member?, guild: Guild?) {
        val content = message.contentRaw

        var prefix: String? = null

        val prefixes = prefixFactory.get(guild, author, member, message)

        for (p in prefixes) {
            if (content.startsWith(p)) {
                prefix = p
                break
            }
        }

        if (prefix == null) return

        val split = content.split(" ")

        val (name) = split

        val args = split.toMutableList().apply { remove(name) }

        for (module in modules) {
            if (!module.isLoaded) continue
            val command = module.retrieveCommand(name.removePrefix(prefix))
            if (command != null) {
                if (command.permission.isNotEmpty()) {
                    if (member == null) return
                    command.permission.forEach {
                        if (!member.hasPermission(it)) {
                            message.channel.sendMessage(embed {
                                setAuthor(
                                    "Error $separator ${author.asTag}",
                                    null,
                                    message.jda.selfUser.effectiveAvatarUrl
                                )
                                setColor(Colors.red_400.asColor())
                                +"This command requires ${command.permission.joinToString { perm -> perm.getName() }} to be ran!"
                                setFooter("Command executed by ${author.asTag}", author.effectiveAvatarUrl)
                                setTimestamp(Instant.now())
                            }).queue()
                            return
                        }
                    }
                }
                val container =
                    ArgumentsContainer(serializationFactory, args, command, SerializationContext(author.jda, guild))
                command.execution.invoke(
                    CommandContext(
                        message,
                        author,
                        guild,
                        member,
                        Channel(message.channel, message),
                        container,
                        EnvironmentData(prefixes)
                    )
                )
                return
            }
        }
    }
}