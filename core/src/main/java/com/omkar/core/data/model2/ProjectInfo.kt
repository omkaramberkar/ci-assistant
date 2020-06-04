package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class ProjectInfo(
    @field:Json(name = "owner") val owner: Owner? = null,
    @field:Json(name = "oss") val oss: Boolean? = null,
    @field:Json(name = "reponame") val reponame: String? = null,
    @field:Json(name = "parallel") val parallel: Int? = null,
    @field:Json(name = "username") val username: String? = null,
    @field:Json(name = "analytics_id") val analyticsId: String? = null,
    @field:Json(name = "scopes") val scopes: List<String>? = null,
    @field:Json(name = "has_usable_key") val hasUsableKey: Boolean? = null,
    @field:Json(name = "vcs_type") val vcsType: String? = null,
    @field:Json(name = "jira") val jira: Any? = null,
    @field:Json(name = "vcs_url") val vcsUrl: String? = null,
    @field:Json(name = "following") val following: Boolean? = null,
    @field:Json(name = "default_branch") val defaultBranch: String? = null
)
