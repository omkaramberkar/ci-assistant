package com.omkar.core.domain

import com.omkar.core.data.CircleCIRepository
import com.omkar.core.data.model2.Pipeline
import com.omkar.core.di.IoDispatcher
import com.omkar.core.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCircleCIPipelinesUseCase @Inject constructor(
    private val repository: CircleCIRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    // -----------------------------------------------------------------------------------------
    // Public functions
    // -----------------------------------------------------------------------------------------

    suspend operator fun invoke(
        vcsType: String,
        username: String,
        repoName: String
    ): Result<List<Pipeline>> {
        return withContext(ioDispatcher) {
            repository.getPipelines(vcsType, username, repoName)
        }
    }
}
