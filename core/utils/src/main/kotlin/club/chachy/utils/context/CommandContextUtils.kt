package club.chachy.utils.context

import club.chachy.aura.command.data.executor.CommandContext
import club.chachy.utils.embed
import club.chachy.utils.separator
import com.github.fcannizzaro.material.Colors
import java.time.Instant

fun CommandContext.error(reason: String) {
    channel.reply(embed {
        setAuthor("Error $separator ${author.asTag}", null, message.jda.selfUser.effectiveAvatarUrl)
        setColor(Colors.red_400.asColor())
        +reason
        setFooter("Command executed by ${author.asTag}", author.effectiveAvatarUrl)
        setTimestamp(Instant.now())
    })

}

fun CommandContext.success(reason: String) {
    channel.reply(embed {
        setAuthor("Success! $separator ${author.asTag}", null, message.jda.selfUser.effectiveAvatarUrl)
        setColor(Colors.green_400.asColor())
        +reason
        setFooter("Requested by ${author.asTag}", author.effectiveAvatarUrl)
        setTimestamp(Instant.now())
    })
}