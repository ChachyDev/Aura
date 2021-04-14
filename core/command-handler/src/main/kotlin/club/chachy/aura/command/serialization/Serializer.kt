package club.chachy.aura.command.serialization

interface Serializer<T> {
    fun serialize(data: String) : T?
}