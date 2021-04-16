package club.chachy.modules

import club.chachy.aura.dsl.Aura
import club.chachy.core.core
import club.chachy.moderation.moderation

fun Aura.createModules() {
    core()
    moderation()
}