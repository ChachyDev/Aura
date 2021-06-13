package club.chachy

import club.chachy.aura.dsl.dsl.aura
import club.chachy.aura.factory.GuildPrefixFactory
import club.chachy.config.config
import club.chachy.config.spec.BotSpec
import club.chachy.config.spec.DatabaseSpec
import club.chachy.database.AuraDatabase
import club.chachy.modules.createModules
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.cache.CacheFlag

fun main() {
    AuraDatabase.start(
        config[DatabaseSpec.url],
        config[DatabaseSpec.driver],
        config[DatabaseSpec.user],
        config[DatabaseSpec.password]
    )
    aura(config[BotSpec.token], prefixFactory = GuildPrefixFactory()) {
        createModules()

        engine {
            setChunkingFilter(ChunkingFilter.ALL)
            disableCache(CacheFlag.CLIENT_STATUS, CacheFlag.ACTIVITY)
            enableIntents(GatewayIntent.GUILD_MEMBERS)
            setMemberCachePolicy(null)
            setLargeThreshold(250)
        }
    }.login()
}