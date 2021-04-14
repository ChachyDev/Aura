package club.chachy.aura.dsl.dsl

import club.chachy.aura.dsl.Aura

fun aura(token: String, aura: Aura.() -> Unit) = Aura(token).apply(aura)