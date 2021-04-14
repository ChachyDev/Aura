package club.chachy.aura.command.data.executor

import club.chachy.aura.command.args.ArgumentsContainer
import net.dv8tion.jda.api.entities.*

data class CommandExecutor(
    val message: Message,
    val author: User,
    val guild: Guild?,
    val member: Member?,
    val channel: Channel,
    val args: ArgumentsContainer
) {
    fun reply(content: String) = channel.reply(content)
}

/**
 * Cleaner version of JDA's [MessageChannel] :)
 */
data class Channel(private val channel: MessageChannel, private val message: Message) {
    fun send(content: String) = channel.sendMessage(content).queue()

    fun sendBlocking(content: String): Message = channel.sendMessage(content).complete()

    fun reply(message: Message, content: String) = message.reply(content).queue()

    fun replyBlocking(message: Message, content: String) = message.reply(content).queue()

    fun reply(content: String) = message.reply(content).queue()

    fun replyBlocking(content: String): Message = message.reply(content).complete()
}