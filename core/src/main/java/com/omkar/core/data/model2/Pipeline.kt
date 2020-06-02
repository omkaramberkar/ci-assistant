package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class Pipeline(
    @field:Json(name = "id") val id: String? = null,
    @field:Json(name = "errors") val errors: List<Error>? = null,
    @field:Json(name = "project_slug") val projectSlug: String? = null,
    @field:Json(name = "updated_at") val updatedAt: String? = null,
    @field:Json(name = "number") val number: Int? = null,
    @field:Json(name = "state") val state: String? = null,
    @field:Json(name = "created_at") val createdAt: String? = null,
    @field:Json(name = "trigger") val trigger: Trigger? = null,
    @field:Json(name = "vcs") val vcs: Vcs? = null,
    val workflows: List<Workflow>? = null
)
