package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class Vcs(
    @field:Json(name = "origin_repository_url") val originRepositoryUrl: String? = null,
    @field:Json(name = "target_repository_url") val targetRepositoryUrl: String? = null,
    @field:Json(name = "revision") val revision: String? = null,
    @field:Json(name = "provider_name") val providerName: String? = null,
    @field:Json(name = "commit") val commit: Commit? = null,
    @field:Json(name = "branch") val branch: String? = null
)
