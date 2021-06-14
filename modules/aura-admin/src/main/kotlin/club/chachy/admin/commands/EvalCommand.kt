package club.chachy.admin.commands

import club.chachy.admin.utils.block
import club.chachy.admin.utils.toCodeBlock
import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.utils.embed
import club.chachy.utils.separator
import com.github.fcannizzaro.material.Colors
import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import java.time.Instant
import javax.script.ScriptEngineManager

private val CODE_HEADER = """
    val ctx = bindings["ctx"] as club.chachy.aura.command.data.executor.CommandContext
    
    
""".trimIndent()

private val engine = ScriptEngineManager().getEngineByName("kotlin")

private var isSet = false

fun setIdea() {
    if (!isSet) {
        setIdeaIoUseFallback()
        engine.eval("1 + 1")
        isSet = true
    }
}

fun Module.eval() = command("eval", "<code>") {
    setIdea()

    val code = args.raw.joinToString(" ")
    val (stripped, language) = code.block()

    val bindings = engine.createBindings().apply {
        put("ctx", this@command)
    }

    val result = runCatching { engine.eval(CODE_HEADER + stripped, bindings) }.getOrNull()

    val isSuccess = result != null

    channel.reply(embed {
        setAuthor("${if (isSuccess) "Success!" else "Failed :("} $separator ${author.asTag}", null, message.jda.selfUser.effectiveAvatarUrl)
        setColor(if (isSuccess) Colors.green_400.asColor() else Colors.red_400.asColor())

        +"Code:\n${stripped.toCodeBlock(language)}\nResult:\n${result.toString().toCodeBlock("")}"

        setFooter("Requested by ${author.asTag}", author.effectiveAvatarUrl)
        setTimestamp(Instant.now())
    })
}