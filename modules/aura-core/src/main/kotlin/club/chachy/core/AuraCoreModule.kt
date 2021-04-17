package club.chachy.core

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.Aura
import club.chachy.aura.dsl.dsl.module
import club.chachy.core.commands.prefix

class AuraCoreModule(aura: Aura) : Module("Core") {
    init {
        aura.module(this)
        prefix()
    }
}