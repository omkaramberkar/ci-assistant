package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class Project(
    @field:Json(name = "reponame") val repoName: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "scopes") val scopes: List<String>,
    @field:Json(name = "vcs_type") val vcsType: String,
    @field:Json(name = "vcs_url") val vcsUrl: String,
    @field:Json(name = "default_branch") val defaultBranch: String
)
