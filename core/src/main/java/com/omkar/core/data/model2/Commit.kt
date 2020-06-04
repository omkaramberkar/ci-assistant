package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class Commit(
    @field:Json(name = "body") val body: String? = null,
    @field:Json(name = "subject") val subject: String? = null
)
