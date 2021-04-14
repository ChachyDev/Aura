package club.chachy.aura.command.data

import club.chachy.aura.command.data.executor.CommandExecutor

data class Command(val name: String, val description: String, val argsSpec: String, val execution: CommandExecutor.() -> Unit)