package club.chachy.aura.command.serialization.default.serializers

import club.chachy.aura.command.serialization.Serializer

class StringSerializer : Serializer<String> {
    override fun serialize(data: String) = data
}