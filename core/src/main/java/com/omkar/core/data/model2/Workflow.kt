package com.omkar.core.data.model2

import com.squareup.moshi.Json

class Workflow(
    @field:Json(name = "pipeline_id") val pipelineId: String? = null,
    @field:Json(name = "id") val id: String? = null,
    @field:Json(name = "name") val name: String? = null,
    @field:Json(name = "project_slug") val projectSlug: String? = null,
    @field:Json(name = "status") val status: Status? = null,
    @field:Json(name = "started_by") val startedBy: String? = null,
    @field:Json(name = "pipeline_number") val pipelineNumber: Int? = null,
    @field:Json(name = "created_at") val createdAt: String? = null,
    @field:Json(name = "stopped_at") val stoppedAt: String? = null
)
    