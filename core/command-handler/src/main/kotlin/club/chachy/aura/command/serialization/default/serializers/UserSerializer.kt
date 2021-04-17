package club.chachy.aura.command.serialization.default.serializers

import club.chachy.aura.command.serialization.SerializationContext
import club.chachy.aura.command.serialization.Serializer
import net.dv8tion.jda.api.entities.User

open class UserSerializer : Serializer<User> {
    private val MENTION_REGEX = "<@!?\\d{17,20}>".toRegex()

    private val NAME_DISCRIM_REGEX = ".{2,32}#\\d{4}".toRegex()

    override fun serialize(context: SerializationContext, data: String): User? {
        val id = data.toLongOrNull()

        if (id != null) {
            return context.guild?.getMemberById(id)?.user ?: context.jda.getUserById(id)
        }

        if (data.matches(MENTION_REGEX)) {
            return context.guild?.getMemberById(data.numbersOnly())?.user ?: context.jda.getUserById(data.numbersOnly())
        }

        if (data.matches(NAME_DISCRIM_REGEX)) {
            val nameDiscrim = data.split("#")
            return context.guild?.members?.find { it.user.name == nameDiscrim[0] && it.user.discriminator == nameDiscrim[1] }?.user
                ?: context.jda.users.find { it.name == nameDiscrim[0] && it.discriminator == nameDiscrim[1] }
        }

        return null
    }

    private fun String.numbersOnly() = replace("[^0-9]".toRegex(), "")
}