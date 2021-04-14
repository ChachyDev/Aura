package club.chachy.aura.dsl.dsl

import club.chachy.aura.command.factory.PrefixFactory
import club.chachy.aura.command.factory.default.DefaultPrefixFactory
import club.chachy.aura.dsl.Aura

fun aura(
    token: String,
    prefixFactory: PrefixFactory = DefaultPrefixFactory(listOf(System.getenv("PREFIX") ?: "a!")),
    aura: Aura.() -> Unit
) = Aura(token, prefixes = listOf(System.getenv("PREFIX") ?: "a!"), prefixFactory = prefixFactory).apply(aura)