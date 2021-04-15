package club.chachy.aura.command.data

import club.chachy.aura.command.data.executor.CommandExecutor
import net.dv8tion.jda.api.Permission

data class Command(val name: String, val description: String, val argsSpec: String, val execution: CommandExecutor.() -> Unit, val permission: Collection<Permission>)