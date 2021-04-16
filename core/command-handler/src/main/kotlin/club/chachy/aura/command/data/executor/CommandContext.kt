package club.chachy.aura.command.data.executor

import club.chachy.aura.command.args.ArgumentsContainer
import club.chachy.aura.command.data.executor.data.Channel
import club.chachy.utils.embed
import club.chachy.utils.separator
import com.github.fcannizzaro.material.Colors
import net.dv8tion.jda.api.entities.*
import java.time.Instant

data class CommandContext(
    val message: Message,
    val author: User,
    val guild: Guild?,
    val member: Member?,
    val channel: Channel,
    val args: ArgumentsContainer
) {
    fun reply(content: String) = channel.reply(content)

    fun error(reason: String) {
        channel.reply(embed {
            setAuthor("Error $separator ${author.asTag}", null, message.jda.selfUser.effectiveAvatarUrl)
            setColor(Colors.red_400.asColor())
            +reason
            setFooter("Command executed by ${author.asTag}", author.effectiveAvatarUrl)
            setTimestamp(Instant.now())
        })

    }

    fun success(reason: String) {
        channel.reply(embed {
            setAuthor("Success! $separator ${author.asTag}", null, message.jda.selfUser.effectiveAvatarUrl)
            setColor(Colors.green_400.asColor())
            +reason
            setFooter("Requested by ${author.asTag}", author.effectiveAvatarUrl)
            setTimestamp(Instant.now())
        })
    }
}