package com.omkar.core.data.model

import com.squareup.moshi.Json

data class BuildInfo(
    @field:Json(name = "outcome") val outcome: String,
    @field:Json(name = "status") val status: String,
    @field:Json(name = "build_num") val buildNum: Int,
    @field:Json(name = "vcs_revision") val vcsRevision: String,
    @field:Json(name = "pushed_at") val pushedAt: String,
    @field:Json(name = "is_workflow_job") val isWorkflowJob: Boolean,
    @field:Json(name = "is_2_0_job") val is20Job: Boolean,
    @field:Json(name = "added_at") val addedAt: String
)
