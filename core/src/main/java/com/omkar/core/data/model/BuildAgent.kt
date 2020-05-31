package com.omkar.core.data.model

import com.squareup.moshi.Json

data class BuildAgent(
    @field:Json(name = "image") val image: String?,
    @field:Json(name = "properties") val properties: Properties?
)
