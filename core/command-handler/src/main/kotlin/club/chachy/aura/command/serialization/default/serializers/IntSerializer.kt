package club.chachy.aura.command.serialization.default.serializers

import club.chachy.aura.command.serialization.Serializer

class IntSerializer : Serializer<Int> {
    override fun serialize(data: String) = data.toIntOrNull()
}