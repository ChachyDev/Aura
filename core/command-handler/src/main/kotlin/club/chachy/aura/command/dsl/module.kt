package club.chachy.aura.command.dsl

import club.chachy.aura.command.data.Command
import club.chachy.aura.command.data.Module
import club.chachy.aura.command.data.executor.CommandExecutor
import club.chachy.aura.dsl.Aura

fun Aura.module(name: String, block: Module.() -> Unit) {
    val module = Module(name).apply(block)
    commandHandler.registerModule(module)
}

fun Module.command(name: String, spec: String = "", description: String = "", block: CommandExecutor.() -> Unit) {
    registerCommand(Command(name, description, spec, block))
}