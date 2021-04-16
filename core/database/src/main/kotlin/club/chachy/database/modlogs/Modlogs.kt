package club.chachy.database.modlogs

import club.chachy.database.modlogs.modlog.Modlog
import org.jetbrains.exposed.sql.Table

object Modlogs : Table() {
    val id = long("modlog_id")

    val userId = long("user_id")

    val type = enumeration("modlog_type", Modlog::class)

    val reason = varchar("reason", 128)

    override val primaryKey = PrimaryKey(id, name = "PK_Modlogs_ID")
}