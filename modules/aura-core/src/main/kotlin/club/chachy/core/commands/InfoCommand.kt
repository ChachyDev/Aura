package club.chachy.core.commands

import club.chachy.aura.dsl.dsl.command
import club.chachy.aura.version.Version
import club.chachy.core.CoreModule
import club.chachy.core.utils.humanUptime
import club.chachy.core.utils.programMemory
import club.chachy.core.utils.totalMemory
import club.chachy.utils.heart
import club.chachy.utils.send
import club.chachy.utils.separator
import com.github.fcannizzaro.material.Colors
import java.time.Instant

fun CoreModule.info() = command("info") {
    val prefixes = environment.prefixes
    val plural = if (prefixes.size > 1) "es" else ""

    channel.send {
        setAuthor("Aura $separator Info", null, message.jda.selfUser.effectiveAvatarUrl)
        setColor(Colors.green_400.asColor())
        field("$separator Uptime", humanUptime, true)
        field("$separator Memory", "${programMemory}MiB / ${totalMemory}MiB", true)
        field("$separator Version", Version.version, true)
        field("$separator Guilds", message.jda.guilds.size, true)
        field("$separator Users", message.jda.users.size, true)
        field("$separator Prefix$plural", prefixes.joinToString(), true)
        setFooter("Powered by JDA and Love $heart", author.effectiveAvatarUrl)
        setTimestamp(Instant.now())
    }
}