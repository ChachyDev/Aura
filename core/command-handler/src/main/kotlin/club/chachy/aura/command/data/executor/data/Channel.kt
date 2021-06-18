package club.chachy.aura.command.data.executor.data

import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.MessageChannel
import net.dv8tion.jda.api.entities.MessageEmbed
import java.io.File

private val allowedMentions = listOf(
    Message.MentionType.CHANNEL,
    Message.MentionType.EMOTE,
    Message.MentionType.USER
)

/**
 * Cleaner version of JDA's [MessageChannel] :)
 */
data class Channel(
    private val channel: MessageChannel,
    private val message: Message,
    private val messageToEdit: Message?
) {
    val name = channel.name
    val id = channel.idLong

    private fun reply(message: Message, content: String) =
        messageToEdit?.editMessage(message) ?: message.reply(content).allowedMentions(allowedMentions)
    private fun reply(message: Message, embed: MessageEmbed) =
        messageToEdit?.editMessage(embed) ?: message.reply(embed).allowedMentions(allowedMentions)

    fun sendBlocking(content: String): Message =
        channel.sendMessage(content).allowedMentions(allowedMentions).complete()

    fun replyBlocking(message: Message, content: MessageEmbed): Message = reply(message, content).complete()
    fun replyBlocking(message: Message, content: String): Message = reply(message, content).complete()
    fun replyBlocking(content: MessageEmbed): Message = reply(message, content).complete()
    fun replyBlocking(content: String): Message = reply(message, content).complete()

    fun send(content: MessageEmbed) =
        messageToEdit?.editMessage(content)?.allowedMentions(allowedMentions)?.queue() ?: channel.sendMessage(
            content
        ).allowedMentions(allowedMentions).queue()

    fun send(content: String) =
        messageToEdit?.editMessage(content)?.allowedMentions(allowedMentions)?.queue() ?: channel.sendMessage(content)
            .allowedMentions(allowedMentions).queue()

    fun send(file: File) = channel.sendFile(file)
        .allowedMentions(allowedMentions).queue()

    fun reply(content: String) = reply(message, content).queue()
    fun reply(content: MessageEmbed) = reply(message, content).queue()

    fun toJDAChannel() = channel

    override fun toString() = name
}