package club.chachy.admin

import club.chachy.admin.commands.*
import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.Aura
import club.chachy.aura.dsl.dsl.module
import net.dv8tion.jda.api.JDA
import org.jetbrains.kotlin.cli.common.environment.setIdeaIoUseFallback
import javax.script.ScriptEngineManager

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

        setIdeaIoUseFallback()
        eval(ScriptEngineManager().getEngineByName("kotlin"), aura)

        import()
    }
}