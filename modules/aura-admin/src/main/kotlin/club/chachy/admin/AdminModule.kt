package club.chachy.admin

import club.chachy.admin.commands.load
import club.chachy.admin.commands.unload
import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.Aura
import club.chachy.aura.dsl.dsl.module
import net.dv8tion.jda.api.JDA

@Suppress("ObjectPropertyName")
val _admins = ArrayList<Long>()

val JDA.admins: List<Long>
    get() {
        if (_admins.isNotEmpty()) return _admins
        _admins.addAll(retrieveApplicationInfo().complete().team?.members?.map { it.user.idLong }
            ?: error("Failed to get team members!"))
        return _admins
    }


class AdminModule(aura: Aura) : Module("Admin", passPredicate = { jda.admins.any { it == idLong } }) {
    init {
        aura.module(this)

        unload(aura.commandHandler)
        load(aura.commandHandler)
    }
}