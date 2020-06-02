package com.omkar.core.data

import com.omkar.core.data.api.CircleCIService
import com.omkar.core.data.model.Build
import com.omkar.core.data.model.Me
import com.omkar.core.data.model2.Pipeline
import com.omkar.core.data.model2.Project
import com.omkar.core.data.model2.ProjectInfo
import com.omkar.core.data.model2.Workflows
import com.omkar.core.result.Result
import java.io.IOException
import javax.inject.Inject

class CircleCIRemoteDataSource @Inject constructor(private val service: CircleCIService) {

    // -----------------------------------------------------------------------------------------
    // Public functions
    // -----------------------------------------------------------------------------------------

    suspend fun getCircleCIUser(token: String): Result<Me> {
        try {
            val response = service.getMe(token)
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

    suspend fun getProjects(token: String): Result<List<Project>> {
        try {
            val response = service.getProjects(token)
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

    suspend fun getProjectInfo(
        vcsType: String,
        username: String,
        project: String,
        circleToken: String
    ): Result<ProjectInfo> {
        try {
            val response = service.getProjectInfo(vcsType, username, project, circleToken)
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

    suspend fun getPipelines(
        vcsType: String,
        username: String,
        project: String,
        circleToken: String
    ): Result<List<Pipeline>> {
        try {
            val pipelinesResponse = service.getPipelines(vcsType, username, project, circleToken)
            if (pipelinesResponse.isSuccessful) {
                val pipelinesResponseBody = pipelinesResponse.body()
                if (pipelinesResponseBody != null) {
                    val pipelinesWithWorkflow = mutableListOf<Pipeline>()
                    pipelinesResponseBody.pipelines?.forEach { pipeline ->
                        pipeline.id?.let { id ->
                            val workflowsResponse = service.getWorkflows(id, circleToken)
                            if (workflowsResponse.isSuccessful) {
                                val workflowsResponseBody = workflowsResponse.body()
                                if (workflowsResponseBody != null) {
                                    pipelinesWithWorkflow.add(
                                        pipeline.copy(workflows = workflowsResponseBody.workflows)
                                    )
                                }
                            }
                        }
                    }
                    return Result.Success(pipelinesWithWorkflow)
                }
            }
            return Result.Error(IOException("Error getting user ${pipelinesResponse.code()}"))
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.Error(IOException("Error getting user", e))
        }
    }

    suspend fun getWorkflows(
        pipelineId: String,
        circleToken: String
    ): Result<Workflows> {
        try {
            val response = service.getWorkflows(pipelineId, circleToken)
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
