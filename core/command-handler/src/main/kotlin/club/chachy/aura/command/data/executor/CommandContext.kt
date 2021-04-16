package club.chachy.aura.command.data.executor

import club.chachy.aura.command.args.ArgumentsContainer
import club.chachy.aura.command.data.executor.data.Channel
import net.dv8tion.jda.api.entities.*

data class CommandContext(
    val message: Message,
    val author: User,
    val guild: Guild?,
    val member: Member?,
    val channel: Channel,
    val args: ArgumentsContainer
) {
    fun reply(content: String) = channel.reply(content)
}