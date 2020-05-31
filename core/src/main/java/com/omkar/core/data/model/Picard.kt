package com.omkar.core.data.model

import com.squareup.moshi.Json

data class Picard(
    @field:Json(name = "build_agent") val buildAgent: BuildAgent?,
    @field:Json(name = "resource_class") val resourceClass: ResourceClass?,
    @field:Json(name = "executor") val executor: String?
)
