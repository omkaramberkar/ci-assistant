package com.omkar.core.data.model

import com.squareup.moshi.Json
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

data class Build(
    @field:Json(name = "compare") val compare: Any?,
    @field:Json(name = "previous_successful_build") val previousSuccessfulBuild: PreviousSuccessfulBuild?,
    @field:Json(name = "build_parameters") val buildParameters: BuildParameters?,
    @field:Json(name = "oss") val oss: Boolean?,
    @field:Json(name = "all_commit_details_truncated") val allCommitDetailsTruncated: Boolean?,
    @field:Json(name = "committer_date") val committerDate: String?,
    @field:Json(name = "steps") val steps: List<Step>?,
    @field:Json(name = "body") val body: String?,
    @field:Json(name = "usage_queued_at") val usageQueuedAt: String?,
    @field:Json(name = "context_ids") val contextIds: List<Any>?,
    @field:Json(name = "fail_reason") val failReason: Any?,
    @field:Json(name = "retry_of") val retryOf: Int?,
    @field:Json(name = "reponame") val reponame: String?,
    @field:Json(name = "ssh_users") val sshUsers: List<Any>?,
    @field:Json(name = "build_url") val buildUrl: String?,
    @field:Json(name = "parallel") val parallel: Int?,
    @field:Json(name = "failed") val failed: Boolean?,
    @field:Json(name = "branch") val branch: String?,
    @field:Json(name = "username") val username: String?,
    @field:Json(name = "author_date") val authorDate: String?,
    @field:Json(name = "why") val why: String?,
    @field:Json(name = "user") val user: User?,
    @field:Json(name = "vcs_revision") val vcsRevision: String?,
    @field:Json(name = "workflows") val workflows: Workflows?,
    @field:Json(name = "owners") val owners: List<String>?,
    @field:Json(name = "vcs_tag") val vcsTag: String?,
    @field:Json(name = "build_num") val buildNum: Int?,
    @field:Json(name = "infrastructure_fail") val infrastructureFail: Boolean?,
    @field:Json(name = "committer_email") val committerEmail: String?,
    @field:Json(name = "has_artifacts") val hasArtifacts: Boolean?,
    @field:Json(name = "previous") val previous: Previous?,
    @field:Json(name = "status") val status: Status?,
    @field:Json(name = "committer_name") val committerName: Any?,
    @field:Json(name = "retries") val retries: Any?,
    @field:Json(name = "subject") val subject: String?,
    @field:Json(name = "vcs_type") val vcsType: VcsType?,
    @field:Json(name = "timedout") val timedout: Boolean?,
    @field:Json(name = "dont_build") val dontBuild: Any?,
    @field:Json(name = "lifecycle") val lifecycle: String?,
    @field:Json(name = "no_dependency_cache") val noDependencyCache: Boolean?,
    @field:Json(name = "stop_time") val stopTime: String?,
    @field:Json(name = "ssh_disabled") val sshDisabled: Boolean?,
    @field:Json(name = "build_time_millis") val buildTimeMillis: Int?,
    @field:Json(name = "picard") val picard: Picard?,
    @field:Json(name = "circle_yml") val circleYml: CircleYml?,
    @field:Json(name = "messages") val messages: List<Message>?,
    @field:Json(name = "is_first_green_build") val isFirstGreenBuild: Boolean?,
    @field:Json(name = "job_name") val jobName: Any?,
    @field:Json(name = "start_time") val startTime: String?,
    @field:Json(name = "canceler") val canceler: Any?,
    @field:Json(name = "all_commit_details") val allCommitDetails: List<AllCommitDetail>?,
    @field:Json(name = "platform") val platform: String?,
    @field:Json(name = "outcome") val outcome: String?,
    @field:Json(name = "vcs_url") val vcsUrl: String?,
    @field:Json(name = "author_name") val authorName: String?,
    @field:Json(name = "node") val node: Any?,
    @field:Json(name = "queued_at") val queuedAt: String?,
    @field:Json(name = "canceled") val canceled: Boolean?,
    @field:Json(name = "author_email") val authorEmail: String?
)

fun Build.vcsRevisionToString(): String {
    return vcsRevision?.substring(0, 7) ?: ""
}

fun Build.buildTimeMillisToString(): String {
    try {
        var different = buildTimeMillis
        different ?: return "-"
        val secondsInMilli = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        val elapsedDays = different / daysInMilli
        different %= daysInMilli

        val elapsedHours = different / hoursInMilli
        different %= hoursInMilli

        val elapsedMinutes = different / minutesInMilli
        different %= minutesInMilli

        val elapsedSeconds = different / secondsInMilli

        return when {
            elapsedMinutes == 0 && elapsedSeconds == 0 -> "-"
            else -> "${elapsedMinutes.asTwoDigitFormat()}:${elapsedSeconds.asTwoDigitFormat()}"
        }
    } catch (e: ParseException) {
        return "-"
    }
}

fun Build.stopTimeToString(): String {
    try {
        val currentStopTime = stopTime
        currentStopTime ?: return "-"
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val startDate: Date? = sdf.parse(currentStopTime)
        startDate ?: return "-"
        val endDate: Date = Calendar.getInstance().time
        var different: Long = endDate.time - startDate.time

        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        val elapsedDays = different / daysInMilli
        different %= daysInMilli

        if (elapsedDays > 0) {
            return when (elapsedDays) {
                1L -> "$elapsedDays day ago"
                else -> "$elapsedDays days ago"
            }
        }

        val elapsedHours = different / hoursInMilli
        different %= hoursInMilli

        if (elapsedHours > 0) {
            return when (elapsedHours) {
                1L -> "$elapsedHours hour ago"
                else -> "$elapsedHours hours ago"
            }
        }

        val elapsedMinutes = different / minutesInMilli
        different %= minutesInMilli

        if (elapsedMinutes > 0) return "$elapsedMinutes min ago"

        val elapsedSeconds = different / secondsInMilli

        if (elapsedSeconds > 0) return "$elapsedSeconds sec ago"

        return "-"
    } catch (e: ParseException) {
        return "-"
    }
}

fun Int?.asTwoDigitFormat(): String? {
    return when {
        this == null -> null
        else -> String.format("%02d", this)
    }
}
