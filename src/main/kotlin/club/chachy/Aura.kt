package club.chachy

import club.chachy.aura.dsl.dsl.aura
import club.chachy.constants.TOKEN_ENV
import club.chachy.modules.createModules
import club.chachy.utils.env

fun main() {
    aura(env(TOKEN_ENV)) {
        createModules()
    }.login()
}