package club.chachy.config.spec

import com.uchuhimo.konf.ConfigSpec

object BotSpec : ConfigSpec() {
    val botPrefix by optional("a!")
    val token by required<String>()
}