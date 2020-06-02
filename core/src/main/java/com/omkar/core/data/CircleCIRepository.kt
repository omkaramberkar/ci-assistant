package com.omkar.core.data

import com.omkar.core.data.model.Build
import com.omkar.core.data.model.Me
import com.omkar.core.data.model2.Pipeline
import com.omkar.core.data.model2.Project
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

    private var cachedProjects: List<Project>? = null

    private var startTime = -1

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

    suspend fun getCircleCIProjects(forceUpdate: Boolean): Result<List<Project>> {
        val circleCiToken = preferenceStorage.circleCIToken
        if (circleCiToken.isNullOrBlank()) {
            throw IllegalStateException("CircleCi Token not registered.")
        }
        val diff = System.currentTimeMillis() - startTime
        val cachedProjects = this.cachedProjects
        if (cachedProjects != null && !forceUpdate
            && (diff >= MIN_NEW_REQUEST_DELAY || startTime == -1)
        ) {
            return Result.Success(cachedProjects)
        }
        val result = remoteDataSource.getProjects(circleCiToken)
        if (result is Result.Success) {
            this.cachedProjects = result.data
        }
        return result
    }

    suspend fun getPipelines(
        vcsType: String,
        username: String,
        project: String
    ): Result<List<Pipeline>> {
        val circleCiToken = preferenceStorage.circleCIToken
        if (circleCiToken.isNullOrBlank()) {
            throw IllegalStateException("CircleCi Token not registered.")
        }
        return remoteDataSource.getPipelines(vcsType, username, project, circleCiToken)
    }

//    suspend fun getWorkflows(forceUpdate: Boolean): Result<Workflows> {
//        val circleCiToken = preferenceStorage.circleCIToken
//        if (circleCiToken.isNullOrBlank()) {
//            throw IllegalStateException("CircleCi Token not registered.")
//        }
//        return remoteDataSource.getWorkflows(circleCiToken)
//    }

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
        private const val MIN_NEW_REQUEST_DELAY = 5_000
    }
}
