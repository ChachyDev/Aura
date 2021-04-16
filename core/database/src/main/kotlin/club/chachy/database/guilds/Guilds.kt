package club.chachy.database.guilds

import org.jetbrains.exposed.sql.Table

object Guilds : Table() {
    val id = long("id")

    val prefix = text("prefix")

    override val primaryKey: PrimaryKey = PrimaryKey(id, name = "PK_Guild_ID")
}