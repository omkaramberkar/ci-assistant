package com.omkar.core.data.model

import com.squareup.moshi.Json

data class Identities(
    @field:Json(name = "bitbucket") val bitbucket: BitbucketIdentity?,
    @field:Json(name = "github") val github: GithubIdentity?
)
