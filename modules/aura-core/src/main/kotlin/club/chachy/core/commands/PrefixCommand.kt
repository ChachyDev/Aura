package club.chachy.core.commands

import club.chachy.aura.command.data.Module
import club.chachy.aura.dsl.dsl.command
import club.chachy.database.guilds.Guilds
import net.dv8tion.jda.api.Permission
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

fun Module.prefix() = command("prefix", "[prefix]", permissions = listOf(Permission.MANAGE_SERVER)) {
    if (guild == null) return@command
    val prefix: String? = args["prefix"]
    if (prefix == null) {
        error("You must specify a prefix!")
        return@command
    }

    val gid = guild?.idLong ?: return@command

    transaction {
        Guilds.update({ Guilds.id eq gid }) {
            it[Guilds.prefix] = prefix
        }
        success("Successfully updated the server's prefix to \"$prefix\"!")
    }
}