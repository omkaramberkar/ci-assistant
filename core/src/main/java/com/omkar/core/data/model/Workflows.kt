package com.omkar.core.data.model

import com.squareup.moshi.Json

data class Workflows(
    @field:Json(name = "job_name") val jobName: String?,
    @field:Json(name = "job_id") val jobId: String?,
    @field:Json(name = "workflow_id") val workflowId: String?,
    @field:Json(name = "workspace_id") val workspaceId: String?,
    @field:Json(name = "upstream_job_ids") val upstreamJobIds: List<String>?,
//    @field:Json(name = "upstream_concurrency_map") val upstreamConcurrencyMap: UpstreamConcurrencyMap?,
    @field:Json(name = "workflow_name") val workflowName: String?
)
