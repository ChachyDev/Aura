package club.chachy

import club.chachy.aura.command.dsl.command
import club.chachy.aura.command.dsl.module
import club.chachy.aura.command.factory.PrefixFactory
import club.chachy.aura.dsl.dsl.aura
import club.chachy.constants.TOKEN_ENV
import club.chachy.utils.env
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.entities.User
import net.dv8tion.jda.api.events.message.GenericMessageEvent

fun main() {
    aura(env(TOKEN_ENV)) {
        module("Test") {
            command("lol", spec = "<test>") {
                val arg = args["test"] ?: 0
                reply("$arg is indeed a cool number!")
            }
        }
    }.login()
}