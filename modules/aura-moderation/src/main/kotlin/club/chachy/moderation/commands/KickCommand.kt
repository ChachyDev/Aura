package club.chachy.moderation.commands

import club.chachy.aura.dsl.dsl.command
import club.chachy.moderation.ModerationModule
import club.chachy.moderation.moderator
import club.chachy.utils.context.error
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException
import net.dv8tion.jda.api.utils.MarkdownUtil.bold

fun ModerationModule.kick() = command("kick", "[member] [reason]", permissions = moderator) {
    if (guild == null) return@command
    val member: Member? = args["member"]
    if (member == null) {
        error("You must specify a member to kick!")
        return@command
    }
    val reason = args["reason"] ?: "No reason provided"

    val kick = { _: Any ->
        try {
            member.kick(reason).queue {
                channel.reply("${bold(member.user.asTag)} has been kicked.")
            }
        } catch (e: Throwable) {
            when(e) {
                is InsufficientPermissionException -> {
                    error("I couldn't not kick ${member.user.asTag} because I don't have ${e.permission.getName()}!")
                }
                else -> {
                    error("An internal error occurred when trying to perform this command. (Exception: ${e.message})")
                }
            }
        }
    }

    member.user.openPrivateChannel().queue {
        it.sendMessage("You have been kicked from \"${guild?.name}\" for \"$reason\"").queue(kick, kick)
    }
}
