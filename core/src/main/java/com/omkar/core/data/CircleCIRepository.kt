package com.omkar.core.data

import com.omkar.core.data.model.Build
import com.omkar.core.data.model.Me
import com.omkar.core.data.prefs.PreferenceStorage
import com.omkar.core.result.Result
import javax.inject.Inject

class CircleCIRepository @Inject constructor(
    private val preferenceStorage: PreferenceStorage,
    private val remoteDataSource: CircleCIRemoteDataSource
) {

    // -----------------------------------------------------------------------------------------
    // Properties
    // -----------------------------------------------------------------------------------------

    private var cachedMe: Me? = null

    private var cachedRecentBuilds: List<Build>? = null

    private var startTime = -1

    // -----------------------------------------------------------------------------------------
    // Initialization
    // -----------------------------------------------------------------------------------------

    init {
        cachedMe = null
    }

    // -----------------------------------------------------------------------------------------
    // Public functions
    // -----------------------------------------------------------------------------------------

    suspend fun getCircleCIUser(circleCiToken: String): Result<Me> {
        val cachedMeModel = this.cachedMe
        if (cachedMeModel != null) {
            return Result.Success(cachedMeModel)
        }
        val result = remoteDataSource.getCircleCIUser(circleCiToken)
        if (result is Result.Success) {
            this.cachedMe = result.data
        }
        return result
    }

    suspend fun getCircleCIRecentBuilds(forceUpdate: Boolean): Result<List<Build>> {
        val circleCiToken = preferenceStorage.circleCIToken
        if (circleCiToken.isNullOrBlank()) {
            throw IllegalStateException("CircleCi Token not registered.")
        }
        val diff = System.currentTimeMillis() - startTime
        val cachedRecentBuilds = this.cachedRecentBuilds
        if (cachedRecentBuilds != null && !forceUpdate
            && (diff >= MIN_NEW_REQUEST_DELAY || startTime == -1)
        ) {
            return Result.Success(cachedRecentBuilds)
        }
        val result = remoteDataSource.getCircleCIRecentBuilds(circleCiToken)
        if (result is Result.Success) {
            this.cachedRecentBuilds = result.data
        }
        return result
    }

    // -----------------------------------------------------------------------------------------
    // Companion
    // -----------------------------------------------------------------------------------------

    companion object {
        private const val MIN_NEW_REQUEST_DELAY = 1_000
    }
}
