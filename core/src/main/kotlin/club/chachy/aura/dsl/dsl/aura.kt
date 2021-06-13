package club.chachy.aura.dsl.dsl

import club.chachy.aura.command.factory.PrefixFactory
import club.chachy.aura.command.factory.default.DefaultPrefixFactory
import club.chachy.aura.dsl.Aura
import club.chachy.config.config
import club.chachy.config.spec.BotSpec

fun aura(
    token: String,
    prefixFactory: PrefixFactory = DefaultPrefixFactory(listOf(config[BotSpec.botPrefix])),
    aura: Aura.() -> Unit
) = Aura(token, prefixFactory = prefixFactory).apply(aura)