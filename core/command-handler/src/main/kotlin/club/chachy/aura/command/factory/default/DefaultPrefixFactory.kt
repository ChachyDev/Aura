package club.chachy.aura.command.factory.default

import club.chachy.aura.command.factory.PrefixFactory
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User

class DefaultPrefixFactory(private val prefixes: List<String>) : PrefixFactory {
    override fun get(guild: Guild?, author: User, member: Member?, message: Message) = prefixes
}