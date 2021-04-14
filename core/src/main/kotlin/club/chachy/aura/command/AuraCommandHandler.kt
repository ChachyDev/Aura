package club.chachy.aura.command

import club.chachy.aura.command.args.ArgumentsContainer
import club.chachy.aura.command.data.executor.Channel
import club.chachy.aura.command.data.executor.CommandExecutor
import club.chachy.aura.command.factory.PrefixFactory
import club.chachy.aura.command.serialization.SerializationFactory
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User

class AuraCommandHandler(
    private val serializationFactory: SerializationFactory,
    private val prefixFactory: PrefixFactory
) : CommandHandler(ArrayList()) {
    override fun handle(message: Message, author: User, member: Member?, guild: Guild?) {
        val content = message.contentRaw

        var prefix = ""

        for (p in prefixFactory.get(guild, author, member, message)) {
            if (content.startsWith(p)) {
                prefix = p
                break
            }
        }

        @Suppress("ReplaceNegatedIsEmptyWithIsNotEmpty")
        if (!prefix.isNotEmpty()) return

        val split = content.split(" ")

        val (name) = split

        val args = split.toMutableList().apply { remove(name) }

        for (module in modules) {
            val command = module.retrieveCommand(name.removePrefix(prefix))
            if (command != null) {
                val container = ArgumentsContainer(serializationFactory, args, command)
                command.execution.invoke(
                    CommandExecutor(
                        message,
                        author,
                        guild,
                        member,
                        Channel(message.channel, message),
                        container
                    )
                )
                return
            }
        }
    }
}