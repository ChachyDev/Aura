package club.chachy.aura.command.data

import club.chachy.aura.command.data.executor.CommandContext
import net.dv8tion.jda.api.Permission

data class Command(
    val name: String,
    val description: String,
    val argsSpec: String,
    val execution: CommandContext.() -> Unit,
    val permission: Collection<Permission>
)