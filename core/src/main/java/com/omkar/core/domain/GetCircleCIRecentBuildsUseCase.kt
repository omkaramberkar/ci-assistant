package com.omkar.core.domain

import com.omkar.core.data.CircleCIRepository
import com.omkar.core.data.model.Build
import com.omkar.core.di.IoDispatcher
import com.omkar.core.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCircleCIRecentBuildsUseCase @Inject constructor(
    private val repository: CircleCIRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    // -----------------------------------------------------------------------------------------
    // Public functions
    // -----------------------------------------------------------------------------------------

    suspend operator fun invoke(parameters: Boolean): Result<List<Build>> {
        return withContext(ioDispatcher) {
            repository.getCircleCIRecentBuilds(parameters)
        }
    }
}
