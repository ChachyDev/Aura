package club.chachy.aura.dsl.dsl

import club.chachy.aura.command.data.Command
import club.chachy.aura.command.data.Module
import club.chachy.aura.command.data.executor.CommandContext
import club.chachy.aura.dsl.Aura
import net.dv8tion.jda.api.Permission

fun Aura.module(name: String, block: Module.() -> Unit) {
    val module = Module(name).apply(block)
    commandHandler.registerModule(module)
}


fun Module.command(
    name: String,
    spec: String = "",
    description: String = "",
    permissions: Collection<Permission> = listOf(),
    block: CommandContext.() -> Unit
) {
    registerCommand(Command(name, description, spec, block, permissions))
}