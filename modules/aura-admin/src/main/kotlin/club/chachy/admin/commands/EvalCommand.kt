package club.chachy.admin.commands

import club.chachy.admin.utils.block
import club.chachy.admin.utils.toCodeBlock
import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.utils.embed
import club.chachy.utils.reply
import club.chachy.utils.separator
import com.github.fcannizzaro.material.Colors
import java.time.Instant
import javax.script.ScriptEngine
import kotlin.math.min

private val CODE_HEADER = """
    ${
    listOf(
        "club.chachy.aura.command.data.executor.*",
        "net.dv8tion.jda.api.events.message.*",
        "net.dv8tion.jda.api.entities.*",
        "net.dv8tion.jda.api.events.*",
        "club.chachy.screenshot.*",
        "club.chachy.database.*",
        "java.io.*"
    ).joinToString("\n") { "import $it" }
}
    
    val ctx = bindings["ctx"] as CommandContext
    
    
""".trimIndent()

fun Module.eval(engine: ScriptEngine) = command("eval", "<code>") {
    val code = args.raw.joinToString(" ")
    val (stripped, language) = code.block()

    val bindings = engine.createBindings().apply {
        put("ctx", this@command)
    }

    val catched =
        runCatching { engine.eval(imports.joinToString("\n") { "import $it" } + CODE_HEADER + stripped, bindings) }

    val result = (catched.getOrNull()?.toString() ?: catched.exceptionOrNull()?.message ?: "No output").truncate(2047)

    channel.reply {
        setAuthor(
            "${if (catched.isSuccess) "Success!" else "Failed :("} $separator ${author.asTag}",
            null,
            message.jda.selfUser.effectiveAvatarUrl
        )
        setColor(if (catched.isSuccess) Colors.green_400.asColor() else Colors.red_400.asColor())

        +"Code:\n${stripped.toCodeBlock(language)}\nResult:\n${result.stripToken().toCodeBlock("")}"

        setFooter("Requested by ${author.asTag}", author.effectiveAvatarUrl)
        setTimestamp(Instant.now())
    }
}

private val regex = "[a-zA-Z0-9_-]{23,28}\\.[a-zA-Z0-9_-]{6,7}\\.[a-zA-Z0-9_-]{27}".toRegex()
private val passwordRegex = "password=.+".toRegex()

private fun String.stripToken(): String {
    return replace(regex, "[removed for security reasons]")
        .replace(passwordRegex, "[removed for security reasons]")
}

private fun String.truncate(max: Int) =
    if (length < max) this
    else substring(0, min(length, max - 3)) + "..."

private fun String.clean(): String {
    return replace("```", "")
}