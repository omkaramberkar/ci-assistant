package com.omkar.ciassistant.ui.main.recentbuilds

import com.omkar.core.data.model2.Pipeline

data class PipelinesResult(
    val success: PipelinesSuccess?,
    val error: Int? = null
)

data class PipelinesSuccess(
    val pipelines: List<Pipeline>?
)
