package com.omkar.core.data.model2

import com.squareup.moshi.Json

enum class Status {
    @field:Json(name = "queued")
    QUEUED,
    @field:Json(name = "not_run")
    NOT_RUN,
    @field:Json(name = "not_running")
    NOT_RUNNING,
    @field:Json(name = "running")
    RUNNING,
    @field:Json(name = "success")
    SUCCESS,
    @field:Json(name = "failed")
    FAILED,
    @field:Json(name = "blocked")
    BLOCKED,
}
