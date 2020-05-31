package com.omkar.core.domain

import com.omkar.core.data.prefs.PreferenceStorage
import com.omkar.core.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveCircleCITokenUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage,
    @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
) : UseCase<String, Unit>(defaultDispatcher) {

    // -----------------------------------------------------------------------------------------
    // UseCaseResult implementation
    // -----------------------------------------------------------------------------------------

    override fun execute(parameters: String) {
        preferenceStorage.circleCIToken = parameters
    }
}
