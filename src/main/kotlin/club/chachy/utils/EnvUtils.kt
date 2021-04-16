package club.chachy.utils

fun env(property: String): String = System.getenv(property) ?: error("Could not find $property in the environment.")

fun envNullable(property: String): String? = System.getenv(property)

fun envOrDefault(property: String, default: String) = envNullable(property) ?: default