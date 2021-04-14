package club.chachy.aura.command.serialization.default

import club.chachy.aura.command.serialization.SerializationFactory
import club.chachy.aura.command.serialization.default.serializers.IntSerializer
import club.chachy.aura.command.serialization.default.serializers.StringSerializer

class DefaultSerializationFactory : SerializationFactory() {
    init {
        registerSerializer(Int::class, IntSerializer())
        registerSerializer(String::class, StringSerializer())
    }
}