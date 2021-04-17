package club.chachy.aura.command.factory

import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User

interface PrefixFactory {
    fun get(guild: Guild?, author: User, member: Member?, message: Message): List<String>
}