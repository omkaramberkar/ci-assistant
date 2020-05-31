package com.omkar.core.data.model

import com.squareup.moshi.Json

data class Previous(
    @field:Json(name = "build_num") val buildNum: Int?,
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "build_time_millis") val buildTimeMillis: Int?
)
