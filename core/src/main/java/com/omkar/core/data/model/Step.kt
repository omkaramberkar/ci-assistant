package com.omkar.core.data.model

import com.squareup.moshi.Json

data class Step(
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "actions") val actions: List<Action>?
)
