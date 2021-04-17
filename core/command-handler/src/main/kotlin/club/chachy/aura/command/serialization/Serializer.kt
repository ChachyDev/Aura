package club.chachy.aura.command.serialization

interface Serializer<T> {
    fun serialize(context: SerializationContext, data: String): T?
}