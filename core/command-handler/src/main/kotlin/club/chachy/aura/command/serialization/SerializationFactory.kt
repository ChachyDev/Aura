package club.chachy.aura.command.serialization

import kotlin.reflect.KClass

open class SerializationFactory {
    val serializers: MutableMap<Class<*>, Serializer<*>> = HashMap()

    fun <T : Any> registerSerializer(clazz: KClass<T>, serializer: Serializer<T>) =
        registerSerializer(clazz.java, serializer)

    fun <T> registerSerializer(clazz: Class<T>, serializer: Serializer<T>) {
        when (clazz) {
            Int::class.java -> serializers[Class.forName("java.lang.Integer")] = serializer
            Byte::class.java -> serializers[Class.forName("java.lang.Byte")] = serializer
            Char::class.java -> serializers[Class.forName("java.lang.Char")] = serializer
            Long::class.java -> serializers[Class.forName("java.lang.Long")] = serializer
            Short::class.java -> serializers[Class.forName("java.lang.Short")] = serializer
            Float::class.java -> serializers[Class.forName("java.lang.Float")] = serializer
            Double::class.java -> serializers[Class.forName("java.lang.Double")] = serializer
            Boolean::class.java -> serializers[Class.forName("java.lang.Boolean")] = serializer
            else -> serializers[clazz] = serializer
        }
    }
}