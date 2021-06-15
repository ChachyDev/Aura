package club.chachy.aura.command.serialization.default

import club.chachy.aura.command.serialization.SerializationFactory
import club.chachy.aura.command.serialization.default.serializers.*
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.User

class DefaultSerializationFactory : SerializationFactory() {
    init {
        registerSerializer(Member::class, MemberSerializer())
        registerSerializer(String::class, StringSerializer())
        registerSerializer(Guild::class, GuildSerializer())
        registerSerializer(Long::class, LongSerializer())
        registerSerializer(User::class, UserSerializer())
        registerSerializer(Int::class, IntSerializer())
    }
}