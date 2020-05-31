package com.omkar.core.data.model

import com.squareup.moshi.Json

data class AllCommitDetail(
    @field:Json(name = "committer_date") val committerDate: String?,
    @field:Json(name = "body") val body: String?,
    @field:Json(name = "author_date") val authorDate: String?,
    @field:Json(name = "committer_email") val committerEmail: String?,
    @field:Json(name = "commit") val commit: String?,
    @field:Json(name = "committer_login") val committerLogin: String?,
    @field:Json(name = "committer_name") val committerName: String?,
    @field:Json(name = "subject") val subject: String?,
    @field:Json(name = "commit_url") val commitUrl: String?,
    @field:Json(name = "author_login") val authorLogin: String?,
    @field:Json(name = "author_name") val authorName: String?,
    @field:Json(name = "author_email") val authorEmail: String?
)
