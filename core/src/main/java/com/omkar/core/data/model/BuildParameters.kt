package com.omkar.core.data.model

import com.squareup.moshi.Json

class BuildParameters(
    @field:Json(name = "CIRCLE_JOB") val circleJob: String?
)
