package club.chachy.moderation.commands

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.database.modlogs.Modlogs
import club.chachy.database.modlogs.modlog.Modlog
import club.chachy.moderation.moderator
import club.chachy.utils.context.error
import club.chachy.utils.context.success
import com.callicoder.snowflake.Snowflake
import net.dv8tion.jda.api.entities.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Module.warn() {
    val snowflake = Snowflake()

    command("warn", "[user] [reason]", permissions = moderator) {
        if (guild == null) return@command
        val user: User? = args["user"]
        if (user == null) {
            error("You must specify a user to warn!")
            return@command
        }
        val reason = args["reason"] ?: "No reason provided"

        if (reason.length > 128) {
            error("Your warn reason must be 128 characters or less!")
            return@command
        }

        transaction {
            Modlogs.insert {
                it[id] = snowflake.nextId()
                it[userId] = user.idLong
                it[Modlogs.reason] = reason
                it[type] = Modlog.Warn
            }

            user.openPrivateChannel().queue {
                it.sendMessage("You have been warned in ${guild?.name} for \"${reason}\"").queue({
                    this@command.success("${user.asTag} has been warned")
                }, {
                    this@command.error("Could not DM ${user.asTag} however the warn has been logged")
                })
            }
        }
    }
}