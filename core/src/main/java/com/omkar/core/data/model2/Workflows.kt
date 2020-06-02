package com.omkar.core.data.model2

import com.squareup.moshi.Json

data class Workflows(
    @field:Json(name = "next_page_token") val nextPageToken: Any? = null,
    @field:Json(name = "items") val workflows: List<Workflow>? = null
)
        