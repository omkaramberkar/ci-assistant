package com.omkar.core.data.model

import com.squareup.moshi.Json

data class Action(
    @field:Json(name = "truncated") val truncated: Boolean?,
    @field:Json(name = "index") val index: Int?,
    @field:Json(name = "parallel") val parallel: Boolean?,
    @field:Json(name = "failed") val failed: Boolean?,
    @field:Json(name = "infrastructure_fail") val infrastructureFail: Boolean?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "bash_command") val bashCommand: String?,
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "timedout") val timedout: Boolean?,
    @field:Json(name = "continue") val _continue: Boolean?,
    @field:Json(name = "end_time") val endTime: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "allocation_id") val allocationId: String?,
    @field:Json(name = "output_url") val outputUrl: String?,
    @field:Json(name = "start_time") val startTime: String?,
    @field:Json(name = "background") val background: Boolean?,
    @field:Json(name = "exit_code") val exitCode: Int?,
    @field:Json(name = "insignificant") val insignificant: Boolean?,
    @field:Json(name = "canceled") val canceled: Boolean?,
    @field:Json(name = "step") val step: Int?,
    @field:Json(name = "run_time_millis") val runTimeMillis: Int?,
    @field:Json(name = "has_output") val hasOutput: Boolean?
)
