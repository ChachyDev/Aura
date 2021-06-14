package club.chachy.modules

import club.chachy.admin.AdminModule
import club.chachy.aura.dsl.Aura
import club.chachy.core.CoreModule
import club.chachy.moderation.ModerationModule

fun Aura.createModules() {
    CoreModule(this)
    AdminModule(this)
    ModerationModule(this)
}