package club.chachy

import club.chachy.aura.dsl.dsl.aura
import club.chachy.database.AuraDatabase
import club.chachy.modules.createModules
import club.chachy.utils.env
import club.chachy.utils.envOrDefault
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.cache.CacheFlag

fun main() {
    AuraDatabase.start(
        env("DATABASE_URL"),
        envOrDefault("DATABASE_DRIVER", "org.postgresql.Driver"),
        env("DATABASE_USER"),
        env("DATABASE_PASSWORD")
    )
    aura(env("BOT_TOKEN")) {
        createModules()

        engine {
            enableIntents(GatewayIntent.GUILD_MEMBERS)
            setMemberCachePolicy(null)
            setChunkingFilter(ChunkingFilter.ALL)
            disableCache(CacheFlag.CLIENT_STATUS, CacheFlag.ACTIVITY)
            setLargeThreshold(250)
        }
    }.login()
}