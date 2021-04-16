package club.chachy.database

import club.chachy.database.guilds.Guilds
import club.chachy.database.modlogs.Modlogs
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object AuraDatabase {
    fun start(url: String, driver: String, username: String, password: String) {
        Database.connect(url, driver, user = username, password = password)
        transaction {
            SchemaUtils.createMissingTablesAndColumns(Guilds, Modlogs)
        }
    }
}