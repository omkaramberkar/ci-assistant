package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class Error(
    @field:Json(name = "type") val type: String? = null,
    @field:Json(name = "message") val message: String? = null
)
