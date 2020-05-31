package com.omkar.core.data.model

import com.squareup.moshi.Json

data class ResourceClass(
    @field:Json(name = "cpu") val cpu: Double?,
    @field:Json(name = "ram") val ram: Int?,
    @field:Json(name = "class") val _class: String?
)
