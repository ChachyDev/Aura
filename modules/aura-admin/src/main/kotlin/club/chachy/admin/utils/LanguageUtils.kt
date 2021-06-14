package club.chachy.admin.utils

private val blockRegex = "```([a-z]*)\\n([\\s\\S]*?)\\n```".toRegex()

fun String.block(): Pair<String, String?> {
    val values = blockRegex.find(this)?.groupValues ?: return this to null

    return values[2] to values[1]
}

fun String.toCodeBlock(language: String?): String {
    return "```${language ?: "kotlin"}\n$this\n```"
}