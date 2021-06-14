package club.chachy.admin.commands

import club.chachy.aura.command.CommandHandler
import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.utils.context.error
import club.chachy.utils.context.success
import java.util.*

fun Module.load(handler: CommandHandler) = command("load", "<name>") {
    val name: String = args.get<String>("name")!!
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    if (handler.load(name)) {
        success("Successfully loaded $name Module")
    } else {
        error("Failed to load $name Module, is it already loaded?")
    }
}