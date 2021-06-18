package club.chachy.admin.commands

import club.chachy.admin.utils.block
import club.chachy.admin.utils.toCodeBlock
import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.Aura
import club.chachy.aura.dsl.dsl.command
import club.chachy.aura.util.http
import club.chachy.utils.reply
import com.github.fcannizzaro.material.Colors
import javax.script.ScriptEngine

private const val INBOX = "\uD83D\uDCE5"

private const val OUTBOX = "\uD83D\uDCE4"

private val CODE_HEADER = """
    ${
    listOf(
        "club.chachy.aura.command.data.executor.*",
        "net.dv8tion.jda.api.events.message.*",
        "io.ktor.client.engine.apache.*",
        "io.ktor.client.features.json.*",
        "net.dv8tion.jda.api.entities.*",
        "net.dv8tion.jda.api.events.*",
        "io.ktor.client.request.*",
        "club.chachy.screenshot.*",
        "club.chachy.database.*",
        "club.chachy.config.*",
        "kotlinx.coroutines.*",
        "io.ktor.client.*",
        "java.io.*"
    ).joinToString("\n") { "import $it" }
}
    
    val http = bindings["http"] as HttpClient
    val aura = bindings["aura"] as club.chachy.aura.dsl.Aura
    val ctx = bindings["ctx"] as CommandContext
    val bot = bindings["aura"] as club.chachy.aura.dsl.Aura
    
    runBlocking {
    
""".trimIndent()

val CODE_FOOTER = """
    
    }
""".trimIndent()

fun Module.eval(engine: ScriptEngine, aura: Aura) = command("eval", "<code>") {
    val code = args.raw.joinToString(" ")
    val (stripped, language) = code.block()

    val bindings = engine.createBindings().apply {
        put("ctx", this@command)
        put("http", http)
        put("aura", aura)
    }

    val start = System.currentTimeMillis()

    val catched =
        runCatching { engine.eval(imports.joinToString("\n") { "import $it" } + CODE_HEADER + stripped + CODE_FOOTER, bindings) }

    val evaluatedTime = System.currentTimeMillis() - start

    val result = catched.getOrNull() ?: catched.exceptionOrNull() ?: "No output"

    channel.reply {
        val (title, color) = if (catched.isSuccess) {
            "Evaluation Complete" to Colors.green_400.asColor()
        } else {
            "Evaluation Failed" to Colors.red_400.asColor()
        }

        setTitle(title)
        setColor(color)

        +"Output Type: ${if (result == "No output") "None" else result::class.java.simpleName}"

        field("$INBOX **Input**", stripped.toCodeBlock(language), false)
        field("$OUTBOX **Output**", result.string().stripToken().toCodeBlock(language), false)

        setFooter("Evaluated in ${evaluatedTime}ms")
    }
}

private val regex = "[a-zA-Z0-9_-]{23,28}\\.[a-zA-Z0-9_-]{6,7}\\.[a-zA-Z0-9_-]{27}".toRegex()
private val passwordRegex = "password=.+".toRegex()

private fun String.stripToken(): String {
    return replace(regex, "[removed for security reasons]")
        .replace(passwordRegex, "[removed for security reasons]")
}

private fun Any.string(): String {
    return when (this) {
        is Throwable -> message ?: stackTraceToString()
        else -> toString()
    }
}