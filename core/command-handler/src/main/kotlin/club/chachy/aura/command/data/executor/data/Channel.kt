package club.chachy.aura.command.data.executor.data

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.MessageEmbed
import java.io.File

/**
 * Cleaner version of JDA's [MessageChannel] :)
 */
data class Channel(private val channel: MessageChannel, private val message: Message) {
    private val allowedMentions = listOf(
        Message.MentionType.CHANNEL,
        Message.MentionType.EMOTE,
        Message.MentionType.USER
    )

    private fun reply(message: Message, content: String) = message.reply(content).allowedMentions(allowedMentions)
    private fun reply(message: Message, embed: MessageEmbed) = message.reply(embed).allowedMentions(allowedMentions)

    fun sendBlocking(content: String): Message =
        channel.sendMessage(content).allowedMentions(allowedMentions).complete()

    fun replyBlocking(message: Message, content: String): Message = reply(message, content).complete()
    fun replyBlocking(content: String): Message = message.reply(content).allowedMentions(allowedMentions).complete()
    fun replyBlocking(message: Message, content: MessageEmbed): Message = reply(message, content).complete()
    fun replyBlocking(content: MessageEmbed): Message =
        message.reply(content).allowedMentions(allowedMentions).complete()

    fun send(content: String) = channel.sendMessage(content).allowedMentions(allowedMentions).queue()
    fun send(content: MessageEmbed) = channel.sendMessage(content).allowedMentions(allowedMentions).queue()
    fun reply(content: String) = reply(message, content).queue()
    fun reply(content: MessageEmbed) = reply(message, content).queue()

    fun toJDAChannel() = channel
}