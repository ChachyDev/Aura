package club.chachy.modules

import club.chachy.admin.AdminModule
import club.chachy.aura.dsl.Aura
import club.chachy.core.CoreModule
import club.chachy.info.InfoModule
import club.chachy.moderation.ModerationModule

fun Aura.createModules() {
    CoreModule(this)
    InfoModule(this)
    AdminModule(this)
    ModerationModule(this)
}