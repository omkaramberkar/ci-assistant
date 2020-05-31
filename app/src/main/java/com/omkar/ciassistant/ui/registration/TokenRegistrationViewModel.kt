package com.omkar.ciassistant.ui.registration

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omkar.ciassistant.R
import com.omkar.ciassistant.ui.LaunchDestination
import com.omkar.core.data.model.Me
import com.omkar.core.domain.GetCircleCITokenUseCase
import com.omkar.core.domain.GetCircleCIUserUseCase
import com.omkar.core.domain.SaveCircleCITokenUseCase
import com.omkar.core.result.Event
import com.omkar.core.result.Result
import com.omkar.core.util.exceptionInDebug
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class TokenRegistrationViewModel @Inject constructor(
    private val resources: Resources,
    private val getCircleCIUserUseCase: GetCircleCIUserUseCase,
    private val saveCircleCITokenUseCase: SaveCircleCITokenUseCase,
    private val getCircleCITokenUseCase: GetCircleCITokenUseCase
) : ViewModel() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    private val _tokenRegistrationFormState = MutableLiveData<TokenRegistrationFormState>()
    val tokenRegistrationFormState: LiveData<TokenRegistrationFormState> =
        _tokenRegistrationFormState

    private val _tokenRegistrationResult = MutableLiveData<TokenRegistrationResult>()
    val tokenRegistrationResult: LiveData<TokenRegistrationResult> =
        _tokenRegistrationResult

    private val _snackbar = MutableLiveData<Event<String>>()
    val snackbar: LiveData<Event<String>> = _snackbar

    private val _launchDestination = MutableLiveData<Event<LaunchDestination>>()
    val launchDestination: LiveData<Event<LaunchDestination>> = _launchDestination

    // -----------------------------------------------------------------------------------------
    // Public functions
    // -----------------------------------------------------------------------------------------

    fun registerToken(token: String) {
        viewModelScope.launch {
            when (val result = getCircleCIUserUseCase(token)) {
                is Result.Success -> {
                    onGetUserSuccess(result.data)
                    saveTokenAndOpenMainActivity(token)
                }
                is Result.Error -> onGetUserError()
            }
        }
    }

    fun tokenUpdated(token: String) {
        if (token.isBlank()) {
            _tokenRegistrationFormState.value = TokenRegistrationFormState(
                circleCiTokenError = R.string.invalid_token
            )
        } else {
            _tokenRegistrationFormState.value = TokenRegistrationFormState(
                isDataValid = true
            )
        }
    }

    // -----------------------------------------------------------------------------------------
    // Private functions
    // -----------------------------------------------------------------------------------------

    private fun onGetUserSuccess(me: Me) {
        val name = getCircleCiUserName(me)
        val avatarUrl = getCircleCiAvatarUrl(me)
        _tokenRegistrationResult.value = TokenRegistrationResult(
            success = TokenRegistrationSuccess(
                name = name,
                avatarUrl = avatarUrl
            )
        )
        _snackbar.value = if (!name.isNullOrBlank()) {
            Event(String.format(resources.getString(R.string.welcome), me.name))
        } else {
            Event(String.format(resources.getString(R.string.welcome), ""))
        }
    }

    private fun onGetUserError() {
        _snackbar.value = Event(resources.getString(R.string.registration_failed))
    }

    private suspend fun saveTokenAndOpenMainActivity(token: String) {
        saveCircleCITokenUseCase(token)
        when (val result = getCircleCITokenUseCase(Unit)) {
            is Result.Success -> onGetTokenSuccess(result.data)
            is Result.Error -> onGetTokenError(result.exception)
        }
    }

    private suspend fun onGetTokenSuccess(data: String?) {
        if (!data.isNullOrBlank()) {
            delay(MIN_SHOW_TIME)
            _launchDestination.value = Event(LaunchDestination.MAIN_ACTIVITY)
        } else {
            throw IllegalStateException("Token not stored to prefs")
        }
    }

    private fun onGetTokenError(exception: Exception) {
        exceptionInDebug(exception)
    }

    private fun getCircleCiUserName(me: Me): String? {
        return me.name
            ?: me.identities?.github?.name
            ?: me.identities?.bitbucket?.name
    }

    private fun getCircleCiAvatarUrl(me: Me): String? {
        return me.avatarUrl
            ?: me.identities?.github?.avatarUrl
            ?: me.identities?.bitbucket?.avatarUrl
    }

    // -----------------------------------------------------------------------------------------
    // Companion
    // -----------------------------------------------------------------------------------------

    companion object {
        private const val MIN_SHOW_TIME = 3_000L
    }
}
