package club.chachy.aura.command.data.executor.data

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.MessageEmbed

/**
 * Cleaner version of JDA's [MessageChannel] :)
 */
data class Channel(private val channel: MessageChannel, private val message: Message) {
    private fun reply(message: Message, content: String) = message.reply(content)
    private fun reply(message: Message, embed: MessageEmbed) = message.reply(embed)

    fun sendBlocking(content: String): Message = channel.sendMessage(content).complete()
    fun replyBlocking(message: Message, content: String): Message = reply(message, content).complete()
    fun replyBlocking(content: String): Message = message.reply(content).complete()
    fun replyBlocking(message: Message, content: MessageEmbed): Message = reply(message, content).complete()
    fun replyBlocking(content: MessageEmbed): Message = message.reply(content).complete()

    fun send(content: String) = channel.sendMessage(content).queue()
    fun send(content: MessageEmbed) = channel.sendMessage(content).queue()
    fun reply(content: String) = reply(message, content).queue()
    fun reply(content: MessageEmbed) = reply(message, content).queue()

    fun toJDAChannel() = channel
}