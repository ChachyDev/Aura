package club.chachy.aura.command.data.executor.channel

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel

/**
 * Cleaner version of JDA's [MessageChannel] :)
 */
data class Channel(private val channel: MessageChannel, private val message: Message) {
    fun send(content: String) = channel.sendMessage(content).queue()
    fun sendBlocking(content: String): Message = channel.sendMessage(content).complete()

    fun reply(message: Message, content: String) = message.reply(content).queue()
    fun reply(content: String) = message.reply(content).queue()
    fun replyBlocking(message: Message, content: String): Message = message.reply(content).complete()
    fun replyBlocking(content: String): Message = message.reply(content).complete()
}