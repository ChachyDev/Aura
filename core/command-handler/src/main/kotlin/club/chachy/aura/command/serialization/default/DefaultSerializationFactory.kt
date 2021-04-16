package club.chachy.aura.command.serialization.default

import club.chachy.aura.command.serialization.SerializationFactory
import club.chachy.aura.command.serialization.default.serializers.IntSerializer
import club.chachy.aura.command.serialization.default.serializers.StringSerializer
import club.chachy.aura.command.serialization.default.serializers.UserSerializer
import net.dv8tion.jda.api.entities.User

class DefaultSerializationFactory : SerializationFactory() {
    init {
        registerSerializer(Int::class, IntSerializer())
        registerSerializer(String::class, StringSerializer())
        registerSerializer(User::class, UserSerializer())
    }
}