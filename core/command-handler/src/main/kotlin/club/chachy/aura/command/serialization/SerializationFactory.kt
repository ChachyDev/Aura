package club.chachy.aura.command.serialization

import kotlin.reflect.KClass

open class SerializationFactory {
    val serializers: MutableMap<Class<*>, Serializer<*>> = HashMap()

    inline fun <reified T : Any> registerSerializer(clazz: KClass<T>, serializer: Serializer<T>) =
        registerSerializer(clazz.java, serializer)

    inline fun <reified T> registerSerializer(clazz: Class<T>, serializer: Serializer<T>) {
        if (T::class.java == Integer::class.java) {
            serializers[Class.forName("java.lang.Integer")] = serializer
        }
        serializers[clazz] = serializer
    }
}