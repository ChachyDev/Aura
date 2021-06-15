package club.chachy.info.commands

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.utils.context.error
import club.chachy.utils.reply
import club.chachy.utils.separator
import net.dv8tion.jda.api.entities.Guild
import java.time.Instant

fun Module.guild() = command("guild", "[guild]") {
    val guild = args["guild"] ?: guild

    guild?.let {
        channel.reply {
            setAuthor(it.name, null, it.iconUrl)
            it.owner?.color?.let { setColor(it) }

            mapOf("Name" to it.name, "ID" to it.id, "Owner" to (it.owner?.user?.asTag ?: "None"), "Roles" to it.roles.filter { it.name != "@everyone" }.joinToString { it.asMention }).entries.forEach {
                field("$separator ${it.key}", it.value, false)
            }

            setFooter("Requested by ${author.asTag}", author.effectiveAvatarUrl)
            setTimestamp(Instant.now())
        }
    } ?: error("You must specify a guild!")
}