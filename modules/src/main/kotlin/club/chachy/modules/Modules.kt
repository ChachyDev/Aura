package club.chachy.modules

import club.chachy.aura.dsl.Aura
import club.chachy.core.AuraCoreModule
import club.chachy.moderation.ModerationModule

fun Aura.createModules() {
    AuraCoreModule(this)
    ModerationModule(this)
}