package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class Owner(
    @field:Json(name = "external_id") val externalId: Int? = null
)
    