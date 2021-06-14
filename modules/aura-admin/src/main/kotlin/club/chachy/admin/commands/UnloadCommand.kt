package club.chachy.admin.commands

import club.chachy.aura.command.CommandHandler
import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.utils.context.error
import club.chachy.utils.context.success
import java.util.*

fun Module.unload(handler: CommandHandler) = command("unload", "<name>") {
    val name: String = args.get<String>("name")!!.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    if (handler.unload(name)) {
        success("Successfully unloaded $name Module")
    } else {
        error("Failed to unload $name Module, is it already unloaded?")
    }
}