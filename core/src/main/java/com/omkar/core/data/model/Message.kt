package com.omkar.core.data.model

import com.squareup.moshi.Json

data class Message(
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "message") val message: String?
)
