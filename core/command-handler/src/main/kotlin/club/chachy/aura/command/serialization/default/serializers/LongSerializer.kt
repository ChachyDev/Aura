package club.chachy.aura.command.serialization.default.serializers

import club.chachy.aura.command.serialization.SerializationContext
import club.chachy.aura.command.serialization.Serializer

class LongSerializer : Serializer<Long> {
    override fun serialize(context: SerializationContext, data: String) = data.toLongOrNull()
}