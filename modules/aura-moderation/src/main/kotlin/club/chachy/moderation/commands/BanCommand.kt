package club.chachy.moderation.commands

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.moderation.moderator
import club.chachy.utils.context.error
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException
import net.dv8tion.jda.api.utils.MarkdownUtil.bold

fun Module.ban() = command("ban", "[member] [reason]", permissions = moderator) {
    if (guild == null) return@command
    val member: Member? = args["member"]
    if (member == null) {
        error("You must specify a member to banned!")
        return@command
    }
    val reason = args["reason"] ?: "No reason provided"

    val kick = { _: Any ->
        try {
            member.ban(7, reason).queue {
                channel.reply("${bold(member.user.asTag)} has been banned.")
            }
        } catch (e: Throwable) {
            when(e) {
                is InsufficientPermissionException -> {
                    error("I couldn't not ban ${member.user.asTag} because I don't have ${e.permission.getName()}!")
                }
                else -> {
                    error("An internal error occurred when trying to perform this command. (Exception: ${e.message})")
                }
            }
        }
    }

    member.user.openPrivateChannel().queue {
        it.sendMessage("You have been banned from \"${guild?.name}\" for \"$reason\"").queue(kick, kick)
    }
}