package club.chachy.modules

import club.chachy.aura.dsl.Aura
import club.chachy.core.CoreModule
import club.chachy.moderation.ModerationModule

fun Aura.createModules() {
    CoreModule(this)
    ModerationModule(this)
}