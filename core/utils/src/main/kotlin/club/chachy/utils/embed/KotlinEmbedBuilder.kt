package club.chachy.utils.embed

import net.dv8tion.jda.api.EmbedBuilder

class KotlinEmbedBuilder : EmbedBuilder() {
    var title: String?
        get() = null
        set(value) {
            setTitle(value)
        }

    operator fun String.unaryPlus() {
        appendDescription(this)
    }
}