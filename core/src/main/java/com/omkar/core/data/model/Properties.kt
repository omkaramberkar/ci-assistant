package com.omkar.core.data.model

import com.squareup.moshi.Json

class Properties(
    @field:Json(name = "build_agent") val buildAgent: String?,
    @field:Json(name = "executor") val executor: String?
)
