package com.omkar.ciassistant.ui.loading

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omkar.ciassistant.ui.LaunchDestination
import com.omkar.core.domain.GetCircleCITokenUseCase
import com.omkar.core.result.Event
import com.omkar.core.result.Result
import com.omkar.core.util.exceptionInDebug
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoadingViewModel @Inject constructor(
    private val getCircleCITokenUseCase: GetCircleCITokenUseCase
) : ViewModel() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    private val _launchDestination = MutableLiveData<Event<LaunchDestination>>()
    val launchDestination: LiveData<Event<LaunchDestination>> = _launchDestination

    init {
        isTokenRegistered()
    }

    // -----------------------------------------------------------------------------------------
    // Private functions
    // -----------------------------------------------------------------------------------------

    private fun isTokenRegistered() {
        viewModelScope.launch {
            when (val result = getCircleCITokenUseCase(Unit)) {
                is Result.Success -> onGetTokenSuccess(result.data)
                is Result.Error -> onGetTokenError(result.exception)
            }
        }
    }

    private suspend fun onGetTokenSuccess(data: String?) {
        delay(MIN_SHOW_TIME)
        if (!data.isNullOrBlank()) {
            _launchDestination.value = Event(LaunchDestination.MAIN_ACTIVITY)
        } else {
            _launchDestination.value = Event(LaunchDestination.TOKEN_REGISTRATION_FRAGMENT)
        }
    }

    private fun onGetTokenError(exception: Exception) {
        exceptionInDebug(exception)
        _launchDestination.value = Event(LaunchDestination.TOKEN_REGISTRATION_FRAGMENT)
    }

    // -----------------------------------------------------------------------------------------
    // Companion
    // -----------------------------------------------------------------------------------------

    companion object {
        private const val MIN_SHOW_TIME = 1_000L
    }
}
