package club.chachy.utils

fun env(property: String): String = System.getenv(property) ?: error("Could not find $property in the environment.")