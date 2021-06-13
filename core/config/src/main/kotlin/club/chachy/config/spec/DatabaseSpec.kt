package club.chachy.config.spec

import com.uchuhimo.konf.ConfigSpec

object DatabaseSpec : ConfigSpec() {
    val password by required<String>()
    val user by optional("postgres")
    val driver by optional("org.postgresql.Driver")
    val url by required<String>()
}