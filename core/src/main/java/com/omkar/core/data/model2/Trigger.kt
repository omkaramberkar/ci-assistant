package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class Trigger(
    @field:Json(name = "received_at") val receivedAt: String? = null,
    @field:Json(name = "type") val type: String? = null,
    @field:Json(name = "actor") val actor: Actor? = null
)
