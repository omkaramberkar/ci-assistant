package com.omkar.core.data.model

import com.squareup.moshi.Json

data class PreviousSuccessfulBuild(
    @field:Json(name = "build_num") val buildNum: Int?,
    @field:Json(name = "status") val status: Status?,
    @field:Json(name = "build_time_millis") val buildTimeMillis: Int?
)
