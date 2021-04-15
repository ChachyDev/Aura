package club.chachy.core.commands

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import net.dv8tion.jda.api.Permission

fun Module.test() = command("test", permissions = listOf(Permission.ADMINISTRATOR)) {
    reply("")
}