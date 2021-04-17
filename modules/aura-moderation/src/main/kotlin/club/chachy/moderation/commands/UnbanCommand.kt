package club.chachy.moderation.commands

import club.chachy.aura.dsl.dsl.command
import club.chachy.moderation.ModerationModule
import club.chachy.moderation.moderator
import club.chachy.utils.context.error
import club.chachy.utils.thinking
import net.dv8tion.jda.api.utils.MarkdownUtil.bold

fun ModerationModule.unban() = command("unban", "[user_id]", permissions = moderator) {
    if (guild == null) return@command
    val user: Long? = args["user_id"]
    if (user == null) {
        error("You must specify a user to unban")
        return@command
    }
    guild?.unban(user.toString())?.queue({
        reply("${bold("Member")} has been unbanned.")
    }, {
        reply("I couldn't seem to find a ban for this person... $thinking")
    })
}