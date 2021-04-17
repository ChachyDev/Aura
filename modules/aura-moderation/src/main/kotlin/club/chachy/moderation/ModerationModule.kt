package club.chachy.moderation

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.Aura
import club.chachy.aura.dsl.dsl.module
import club.chachy.moderation.commands.ban
import club.chachy.moderation.commands.kick
import club.chachy.moderation.commands.unban
import club.chachy.moderation.commands.warn
import net.dv8tion.jda.api.Permission

val moderator = listOf(Permission.MESSAGE_MANAGE)

val admin = listOf(Permission.MANAGE_SERVER)

class ModerationModule(aura: Aura) : Module("Moderation") {
    init {
        aura.module(this)
        warn()
        ban()
        kick()
        unban()
    }
}