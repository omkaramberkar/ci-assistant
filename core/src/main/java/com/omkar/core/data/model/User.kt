package com.omkar.core.data.model

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "is_user") val isUser: Boolean?,
    @field:Json(name = "login") val login: String?,
    @field:Json(name = "avatar_url") val avatarUrl: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "vcs_type") val vcsType: String?,
    @field:Json(name = "id") val id: String?
)
