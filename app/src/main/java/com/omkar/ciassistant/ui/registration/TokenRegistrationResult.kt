package com.omkar.ciassistant.ui.registration

data class TokenRegistrationResult(
    val success: TokenRegistrationSuccess? = null
)

data class TokenRegistrationSuccess(
    val name: String?,
    val avatarUrl: String?
)
