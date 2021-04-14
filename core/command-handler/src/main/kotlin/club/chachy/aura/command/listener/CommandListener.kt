package club.chachy.aura.command.listener

import club.chachy.aura.command.CommandHandler
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class CommandListener(private val commandHandler: CommandHandler) : ListenerAdapter() {
    override fun onGuildMessageUpdate(event: GuildMessageUpdateEvent) {
        commandHandler.handle(event.message, event.author, event.member, event.guild)
    }

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent) {
        commandHandler.handle(event.message, event.author, event.member, event.guild)
    }
}