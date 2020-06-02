package com.omkar.core.data.model

enum class Status(s: String) {
    QUEUED("queued"),
    NOT_RUN("not_run"),
    NOT_RUNNING("not_running"),
    RUNNING("running"),
    SUCCESS("success"),
    FAILED("failed")
}
