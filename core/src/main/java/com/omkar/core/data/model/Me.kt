package com.omkar.core.data.model

import com.squareup.moshi.Json

data class Me(
    @field:Json(name = "enrolled_betas") val enrolledBetas: List<Any>?,
    @field:Json(name = "in_beta_program") val inBetaProgram: Boolean?,
    @field:Json(name = "selected_email") val selectedEmail: String?,
    @field:Json(name = "avatar_url") val avatarUrl: String?,
    @field:Json(name = "trial_end") val trialEnd: String?,
    @field:Json(name = "admin") val admin: Boolean?,
    @field:Json(name = "basic_email_prefs") val basicEmailPrefs: String?,
    @field:Json(name = "sign_in_count") val signInCount: Int?,
    @field:Json(name = "github_oauth_scopes") val githubOauthScopes: List<String>?,
    @field:Json(name = "analytics_id") val analyticsId: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "gravatar_id") val gravatarId: String?,
    @field:Json(name = "first_vcs_authorized_client_id") val firstVcsAuthorizedClientId: String?,
    @field:Json(name = "days_left_in_trial") val daysLeftInTrial: Int?,
    @field:Json(name = "parallelism") val parallelism: Int?,
    @field:Json(name = "student") val student: Boolean?,
    @field:Json(name = "bitbucket_authorized") val bitbucketAuthorized: Boolean?,
    @field:Json(name = "github_id") val githubId: Int?,
    @field:Json(name = "bitbucket") val bitbucket: Bitbucket?,
    @field:Json(name = "dev_admin") val devAdmin: Boolean,
    @field:Json(name = "all_emails") val allEmails: List<String>,
    @field:Json(name = "created_at") val createdAt: String,
    @field:Json(name = "plan") val plan: Any,
    @field:Json(name = "heroku_api_key") val herokuApiKey: String?,
    @field:Json(name = "identities") val identities: Identities?,
    @field:Json(name = "projects") val projects: Map<String, Project>?,
    @field:Json(name = "login") val login: String?,
    //@field:Json(name = "organization_prefs") val organizationPrefs: OrganizationPrefs,
    @field:Json(name = "containers") val containers: Int?,
    @field:Json(name = "pusher_id") val pusherId: String?,
    @field:Json(name = "num_projects_followed") val numProjectsFollowed: Int?
)
