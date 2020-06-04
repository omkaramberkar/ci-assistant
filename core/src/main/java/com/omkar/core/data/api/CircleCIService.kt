package com.omkar.core.data.api

import com.omkar.core.data.model.Me
import com.omkar.core.data.model2.Pipelines
import com.omkar.core.data.model2.Project
import com.omkar.core.data.model2.ProjectInfo
import com.omkar.core.data.model2.Workflows
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CircleCIService {

    @GET(value = "api/v1.1/me")
    @Headers(value = ["Accept: application/json"])
    suspend fun getMe(
        @Query(value = "circle-token") circleToken: String
    ): Response<Me>

    @GET("api/v1/projects")
    @Headers(value = ["Accept: application/json"])
    suspend fun getProjects(
        @Query(value = "circle-token") circleToken: String,
        @Query(value = "shallow") shallow: Boolean = true
    ): Response<List<Project>>

    @GET("api/v2/project/{vcsType}/{username}/{project}/info")
    @Headers(value = ["Accept: application/json"])
    suspend fun getProjectInfo(
        @Path(value = "vcsType") vcsType: String,
        @Path(value = "username") username: String,
        @Path(value = "project") project: String,
        @Query(value = "circle-token") circleToken: String
    ): Response<ProjectInfo>

    @GET("api/v2/project/{vcsType}/{username}/{project}/pipeline")
    @Headers(value = ["Accept: application/json"])
    suspend fun getPipelinesForProject(
        @Path(value = "vcsType") vcsType: String,
        @Path(value = "username") username: String,
        @Path(value = "project") project: String,
        @Query(value = "circle-token") circleToken: String
    ): Response<Pipelines>

    @GET("api/v2/pipeline/{pipelineId}/workflow")
    @Headers(value = ["Accept: application/json"])
    suspend fun getWorkflowsForPipeline(
        @Path(value = "pipelineId") pipelineId: String,
        @Query(value = "circle-token") circleToken: String
    ): Response<Workflows>

    @GET("api/v2/workflow/{workflowId}/job")
    @Headers(value = ["Accept: application/json"])
    suspend fun getJobsForWorkflow(
        @Path(value = "workflowId") workflowId: String,
        @Query(value = "circle-token") circleToken: String
    ): Response<Workflows>

    companion object {
        const val ENDPOINT = "https://circleci.com/"
    }
}
