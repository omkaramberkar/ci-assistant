package com.omkar.core.data

import com.omkar.core.data.api.CircleCIService
import com.omkar.core.data.model.Build
import com.omkar.core.data.model.Me
import com.omkar.core.result.Result
import java.io.IOException
import javax.inject.Inject

class CircleCIRemoteDataSource @Inject constructor(private val service: CircleCIService) {

    // -----------------------------------------------------------------------------------------
    // Public functions
    // -----------------------------------------------------------------------------------------

    suspend fun getCircleCIUser(circleCiToken: String): Result<Me> {
        try {
            val response = service.getMe(circleCiToken)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.Success(body)
                }
            }
            return Result.Error(IOException("Error getting user ${response.code()}"))
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(IOException("Error getting user", e))
        }
    }

    suspend fun getCircleCIRecentBuilds(circleCiToken: String): Result<List<Build>> {
        try {
            val response = service.getRecentBuilds(circleCiToken)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.Success(body)
                }
            }
            return Result.Error(IOException("Error getting recent builds ${response.code()}"))
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(IOException("Error getting recent builds", e))
        }
    }
}
