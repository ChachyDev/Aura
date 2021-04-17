package club.chachy.aura.command.serialization.default.serializers

import club.chachy.aura.command.serialization.SerializationContext
import club.chachy.aura.command.serialization.Serializer
import net.dv8tion.jda.api.entities.Member

class MemberSerializer : Serializer<Member> {
    private val userSerializer = UserSerializer()

    override fun serialize(context: SerializationContext, data: String): Member? {
        return context.guild?.getMember(userSerializer.serialize(context, data) ?: return null)
    }
}