package club.chachy.aura.command.serialization

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild

data class SerializationContext(val jda: JDA, val guild: Guild?)