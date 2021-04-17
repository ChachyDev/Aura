package club.chachy.aura.command.serialization.default

import club.chachy.aura.command.serialization.SerializationFactory
import club.chachy.aura.command.serialization.default.serializers.*
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User

class DefaultSerializationFactory : SerializationFactory() {
    init {
        registerSerializer(Int::class, IntSerializer())
        registerSerializer(Long::class, LongSerializer())
        registerSerializer(String::class, StringSerializer())
        registerSerializer(User::class, UserSerializer())
        registerSerializer(Member::class, MemberSerializer())
    }
}