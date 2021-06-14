package club.chachy.aura.command

import club.chachy.aura.command.data.Module
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User

abstract class CommandHandler(protected val modules: MutableList<Module>) {
    abstract fun handle(message: Message, author: User, member: Member?, guild: Guild?)

    fun registerModule(module: Module) {
        modules.add(module)
    }

    fun unload(name: String): Boolean =
        modules.find { it.name == name }
            .takeIf { it != null }
            ?.let { unload(it) }
            ?: false

    private fun unload(module: Module): Boolean {
        module.isLoaded = false
        return true
    }

    fun load(name: String): Boolean =
        modules.find { it.name == name }
            .takeIf { it != null }
            ?.let { load(it) }
            ?: false

    private fun load(module: Module): Boolean {
        module.isLoaded = true
        return true
    }
}