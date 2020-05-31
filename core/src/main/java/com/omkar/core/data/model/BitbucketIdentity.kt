package com.omkar.core.data.model

import com.squareup.moshi.Json

data class BitbucketIdentity(
    @field:Json(name = "avatar_url") val avatarUrl: String?,
    @field:Json(name = "external_id") val externalId: String?,
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "user?") val user: Boolean?,
    @field:Json(name = "domain") val domain: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "authorized?") val authorized: Boolean?,
    @field:Json(name = "provider_id") val providerId: String?,
    @field:Json(name = "login") val login: String?
)
