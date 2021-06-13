package club.chachy.aura.factory

import club.chachy.aura.command.factory.PrefixFactory
import club.chachy.config.config
import club.chachy.config.spec.BotSpec
import club.chachy.database.guilds.Guilds
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class GuildPrefixFactory(private val default: String = config[BotSpec.botPrefix]) : PrefixFactory {
    override fun get(guild: Guild?, author: User, member: Member?, message: Message): List<String> {
        return transaction {
            if (guild == null) return@transaction listOf(default)

            val prefix = Guilds.select {
                Guilds.id eq guild.idLong
            }.firstOrNull()?.get(Guilds.prefix)
                ?: Guilds.insert {
                    it[id] = guild.idLong
                    it[Guilds.prefix] = config[BotSpec.botPrefix]
                }[Guilds.prefix]

            return@transaction listOf(prefix)
        }
    }
}