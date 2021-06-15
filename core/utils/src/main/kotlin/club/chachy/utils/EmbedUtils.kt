package club.chachy.utils

import club.chachy.aura.command.data.executor.data.Channel
import club.chachy.utils.embed.KotlinEmbedBuilder
import net.dv8tion.jda.api.entities.MessageEmbed

fun embed(block: KotlinEmbedBuilder.() -> Unit): MessageEmbed {
    return KotlinEmbedBuilder().apply(block).build()
}

fun Channel.send(block: KotlinEmbedBuilder.() -> Unit) {
    send(KotlinEmbedBuilder().apply(block).build())
}

fun Channel.reply(block: KotlinEmbedBuilder.() -> Unit) {
    reply(KotlinEmbedBuilder().apply(block).build())
}