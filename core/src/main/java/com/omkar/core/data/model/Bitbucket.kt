package com.omkar.core.data.model

import com.squareup.moshi.Json

data class Bitbucket(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "login") val login: Int?
)
