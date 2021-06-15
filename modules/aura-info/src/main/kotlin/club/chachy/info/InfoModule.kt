package club.chachy.info

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.Aura
import club.chachy.aura.dsl.dsl.module

class InfoModule(aura: Aura) : Module("Info") {
    init {
        aura.module(this)
    }
}