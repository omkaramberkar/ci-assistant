package com.omkar.ciassistant.ui.main.recentbuilds

import com.omkar.core.data.model.Build

data class RecentBuildsResult(
    val success: RecentBuildsSuccess?,
    val error: Int? = null
)

data class RecentBuildsSuccess(
    val recentBuilds: List<Build>
)
