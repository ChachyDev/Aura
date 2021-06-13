package club.chachy.config

import club.chachy.config.spec.BotSpec
import club.chachy.config.spec.DatabaseSpec
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.Spec

val config = Config { addSpecs(BotSpec, DatabaseSpec) }
    .from.env()

fun Config.addSpecs(vararg specs: Spec) {
    specs.forEach {
        addSpec(it)
    }
}