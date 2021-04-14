package club.chachy.aura.dsl

import club.chachy.aura.command.AuraCommandHandler
import club.chachy.aura.command.CommandHandler
import club.chachy.aura.command.factory.PrefixFactory
import club.chachy.aura.command.factory.default.DefaultPrefixFactory
import club.chachy.aura.command.listener.CommandListener
import club.chachy.aura.command.serialization.SerializationFactory
import club.chachy.aura.command.serialization.default.DefaultSerializationFactory
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

class Aura(
    private val token: String,
    private val prefixes: List<String> = listOf("a!"),
    private val serializationFactory: SerializationFactory = DefaultSerializationFactory(),
    private val prefixFactory: PrefixFactory = DefaultPrefixFactory(prefixes),
    val commandHandler: CommandHandler = AuraCommandHandler(serializationFactory, prefixFactory)
) {
    private var _jda: JDA? = null

    private var builder: JDABuilder.() -> Unit = {}

    fun engine(block: JDABuilder.() -> Unit) {
        builder = block
    }

    fun login() {
        _jda = JDABuilder.createDefault(token)
            .addEventListeners(CommandListener(commandHandler))
            .apply(builder)
            .build()
    }
}