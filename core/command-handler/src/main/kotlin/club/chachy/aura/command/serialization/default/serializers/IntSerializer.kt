package club.chachy.aura.command.serialization.default.serializers

import club.chachy.aura.command.serialization.SerializationContext
import club.chachy.aura.command.serialization.Serializer

class IntSerializer : Serializer<Int> {
    override fun serialize(context: SerializationContext, data: String) = data.toIntOrNull()
}