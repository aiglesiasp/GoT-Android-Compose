package com.ipa.dev.gamethrones.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Detail(val characterId: Int)
