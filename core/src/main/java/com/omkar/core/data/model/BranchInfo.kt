package com.omkar.core.data.model

import com.squareup.moshi.Json

data class BranchInfo(
    @field:Json(name = "running_builds") val runningBuilds: List<BuildInfo>? = null,
    @field:Json(name = "recent_builds") val recentBuilds: List<BuildInfo>? = null
)
