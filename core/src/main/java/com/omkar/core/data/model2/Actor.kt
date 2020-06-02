package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class Actor(
    @field:Json(name = "login") val login: String? = null,
    @field:Json(name = "avatar_url") val avatarUrl: String? = null
)
