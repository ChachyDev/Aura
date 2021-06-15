package club.chachy.admin.commands

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.utils.context.success

val imports = ArrayList<String>()

fun Module.import() = command("import", "<import>") {
    val import: String = args["import"]!!
    imports.add(import)

    success("Successfully imported `$import`")
}