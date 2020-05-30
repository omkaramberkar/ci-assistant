package com.omkar.ciassistant

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.omkar.core.domain.GetCircleCITokenUseCase
import com.omkar.core.result.Event
import com.omkar.core.result.Result
import com.omkar.core.result.data
import javax.inject.Inject

class LauncherViewModel @Inject constructor(
    getCircleCITokenUseCase: GetCircleCITokenUseCase
) : ViewModel() {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    private val circleCITokenRegisteredResult: LiveData<Result<String?>> = liveData {
        emit(getCircleCITokenUseCase(Unit))
    }

    val launchDestination = circleCITokenRegisteredResult.map {
        if (it.data == null) {
            Event(LaunchDestination.TOKEN_REGISTRATION_FRAGMENT)
        } else {
            Event(LaunchDestination.MAIN_ACTIVITY)
        }
    }
}

enum class LaunchDestination {
    TOKEN_REGISTRATION_FRAGMENT,
    MAIN_ACTIVITY
}
