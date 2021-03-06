package club.chachy.aura.command.args

import club.chachy.aura.command.data.Command
import club.chachy.aura.command.serialization.SerializationContext
import club.chachy.aura.command.serialization.SerializationFactory
import club.chachy.aura.command.serialization.Serializer

class ArgumentsContainer(
    val factory: SerializationFactory,
    val raw: List<String>,
    command: Command,
    val serializationContext: SerializationContext
) {
    // Maps a spec name to the data
    val rawMapped = HashMap<String, String>()

    @Suppress("UNCHECKED_CAST")
    inline operator fun <reified T> get(name: String): T? {
        val serializer = (factory.serializers[T::class.java] ?: return null) as Serializer<T>

        val data = rawMapped[name] ?: return null

        return serializer.serialize(serializationContext, data)
    }

    init {
        val specSplit = command.argsSpec.split(" ").filter { it.isNotEmpty() }
        for ((i, spec) in specSplit.withIndex()) {
            val isRequired = spec.startsWith("<") && spec.endsWith(">")
            val item = raw.getOrNull(i)
            if (isRequired && item == null) error("You must specify an argument for: ${spec.clean()}")
            if (item != null) {
                rawMapped[spec.clean()] = item
            }
        }
    }

    private fun String.clean() = removePrefix("[").removeSuffix("]").removePrefix("<").removeSuffix(">")
}