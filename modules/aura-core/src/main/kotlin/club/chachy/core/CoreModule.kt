package club.chachy.core

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.Aura
import club.chachy.aura.dsl.dsl.module
import club.chachy.core.commands.info
import club.chachy.core.commands.prefix

class CoreModule(aura: Aura) : Module("Core") {
    val start = System.currentTimeMillis()

    init {
        aura.module(this)
        prefix()
        info()
    }
}