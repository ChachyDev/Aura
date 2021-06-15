package club.chachy.info.commands

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.utils.reply
import club.chachy.utils.separator
import net.dv8tion.jda.api.entities.User
import java.time.Instant

fun Module.user() = command("user", "[user]") {
    val user: User = args["user"] ?: author
    val member = guild?.getMember(user)

    channel.reply {
        setAuthor(user.asTag, user.effectiveAvatarUrl, user.effectiveAvatarUrl)
        member?.color?.let { setColor(it) }

        mapOf("Name" to user.name, "Discriminator" to user.discriminator, "ID" to user.id, "Bot" to user.isBot.toHumanString()).entries.forEach {
            field("$separator ${it.key}", it.value, false)
        }

        member?.let {
            mapOf(
                "Owner" to it.isOwner.toHumanString(),
                "Role Color" to (it.color?.let { c -> "#%02x%02x%02x".format(c.red, c.green, c.blue) } ?: "None"),
                "Join Position" to guild!!.members.sortedBy { member -> member.timeJoined }.indexOf(it) + 1,
                "Roles (${it.roles.size})" to it.roles.joinToString { role -> role.asMention }
            ).entries.forEach { kv ->
                field("$separator ${kv.key}", kv.value, false)
            }
        }

        setFooter("Requested by ${author.asTag}", author.effectiveAvatarUrl)
        setTimestamp(Instant.now())
    }
}

private fun Boolean.toHumanString() = if (this) "Yes" else "No"