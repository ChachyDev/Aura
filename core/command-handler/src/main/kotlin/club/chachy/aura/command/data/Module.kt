package club.chachy.aura.command.data

import net.dv8tion.jda.api.entities.User

open class Module(val name: String, private val cmds: MutableList<Command> = ArrayList(), var isLoaded: Boolean = true, val passPredicate: User.() -> Boolean = { true }) {
    val commands get() = cmds.toList()

    fun registerCommand(command: Command) {
        cmds.add(command)
    }

    fun retrieveCommand(name: String) = cmds.find { it.name.equals(name, true) }
}