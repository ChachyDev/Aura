package club.chachy.moderation

import club.chachy.aura.dsl.Aura
import club.chachy.aura.dsl.dsl.module
import club.chachy.moderation.commands.warn

fun Aura.moderation() {
    module("Moderation") {
        warn()
    }
}