package club.chachy.aura.command.data

open class Module(val name: String, private val cmds: MutableList<Command> = ArrayList()) {
    val commands get() = cmds.toList()

    fun registerCommand(command: Command) {
        cmds.add(command)
    }

    fun retrieveCommand(name: String) = cmds.find { it.name.equals(name, true) }
}