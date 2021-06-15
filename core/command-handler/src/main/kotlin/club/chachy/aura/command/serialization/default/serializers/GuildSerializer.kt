package club.chachy.aura.command.serialization.default.serializers

import club.chachy.aura.command.serialization.SerializationContext
import club.chachy.aura.command.serialization.Serializer
import net.dv8tion.jda.api.entities.Guild

class GuildSerializer : Serializer<Guild> {
    override fun serialize(context: SerializationContext, data: String): Guild? {
        data.toLongOrNull()?.let {
            return context.jda.getGuildById(it)
        }

        return context.jda.getGuildsByName(data, false).getOrNull(0)
    }
}