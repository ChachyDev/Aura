package club.chachy.utils

import club.chachy.utils.embed.KotlinEmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed

fun embed(block: KotlinEmbedBuilder.() -> Unit): MessageEmbed {
    return KotlinEmbedBuilder().apply(block).build()
}