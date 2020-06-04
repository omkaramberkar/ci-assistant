package com.omkar.core.domain

import com.omkar.core.data.CircleCIRepository
import com.omkar.core.data.model2.Project
import com.omkar.core.di.IoDispatcher
import com.omkar.core.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCircleCIProjectsUseCase @Inject constructor(
    private val repository: CircleCIRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    // -----------------------------------------------------------------------------------------
    // Public functions
    // -----------------------------------------------------------------------------------------

    suspend operator fun invoke(parameters: Boolean): Result<List<Project>> {
        return withContext(ioDispatcher) {
            repository.getCircleCIProjects(parameters)
        }
    }
}
