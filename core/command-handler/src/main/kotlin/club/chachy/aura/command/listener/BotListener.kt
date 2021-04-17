package club.chachy.aura.command.listener

import club.chachy.aura.command.CommandHandler
import club.chachy.database.guilds.Guilds
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.MessageUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class BotListener(private val commandHandler: CommandHandler) : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        commandHandler.handle(event.message, event.author, event.member, event.guild)
    }

    override fun onMessageUpdate(event: MessageUpdateEvent) {
        commandHandler.handle(event.message, event.author, event.member, event.guild)
    }

    override fun onGuildJoin(event: GuildJoinEvent) {
        val exists = Guilds.select { Guilds.id eq event.guild.idLong }.firstOrNull() != null
        if (exists) {
            Guilds.deleteWhere { Guilds.id eq event.guild.idLong }
        }
        Guilds.insert {
            it[id] = event.guild.idLong
            it[prefix] = "a!"
        }
    }

    override fun onGuildLeave(event: GuildLeaveEvent) {
        val exists = Guilds.select { Guilds.id eq event.guild.idLong }.firstOrNull() != null
        if (exists) {
            Guilds.deleteWhere { Guilds.id eq event.guild.idLong }
        }
    }
}