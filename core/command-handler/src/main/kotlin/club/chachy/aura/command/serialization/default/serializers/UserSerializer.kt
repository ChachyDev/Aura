package club.chachy.aura.command.serialization.default.serializers

import club.chachy.aura.command.serialization.SerializationContext
import club.chachy.aura.command.serialization.Serializer
import net.dv8tion.jda.api.entities.User

open class UserSerializer : Serializer<User> {
    private val mentionRegex = "<@!?(\\d{17,20})>".toRegex()

    private val nameDiscordRegex = ".{2,32}#\\d{4}".toRegex()

    override fun serialize(context: SerializationContext, data: String): User? {
        val id = data.toLongOrNull()

        if (id != null) {
            return context.guild?.getMemberById(id)?.user ?: context.jda.getUserById(id)
        }

        val mention = mentionRegex.find(data)

        if (mention != null) {
            val userId = mention.groupValues[1]
            return context.guild?.getMemberById(userId)?.user ?: context.jda.getUserById(userId)
        }

        if (data.matches(nameDiscordRegex)) {
            return context.guild?.members?.find { it.user.asTag == data }?.user
                ?: context.jda.users.find { it.asTag == data }
        }

        return null
    }
}