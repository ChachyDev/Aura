package club.chachy.core

import club.chachy.aura.dsl.Aura
import club.chachy.aura.dsl.dsl.module
import club.chachy.core.commands.test

fun Aura.core() = module("Core") {
    test()
}