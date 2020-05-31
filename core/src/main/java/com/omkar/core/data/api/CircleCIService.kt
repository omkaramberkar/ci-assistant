package com.omkar.core.data.api

import com.omkar.core.data.model.Build
import com.omkar.core.data.model.Me
import com.omkar.core.data.model.Project
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

    @GET("projects")
    @Headers(value = ["Accept: application/json"])
    suspend fun getProjects(
        @Query(value = "circle-token") circleToken: String
    ): Response<List<Project>>

    @GET("recent-builds")
    @Headers(value = ["Accept: application/json"])
    suspend fun getRecentBuilds(
        @Query(value = "circle-token") circleToken: String,
        @Query(value = "limit") limit: Int = 30
    ): Response<List<Build>>

    @GET(value = "project/{vcsType}/{username}/{project}")
    @Headers(value = ["Accept: application/json"])
    suspend fun getRecentBuildsForProject(
        @Path(value = "vcsType") vcsType: String,
        @Path(value = "username") username: String,
        @Path(value = "project") project: String,
        @Query(value = "circle-token") circleToken: String,
        @Query(value = "limit") limit: Int = 30
    ): Response<List<Build>>

    @GET("project/{vcsType}/{username}/{project}/tree/{branch}")
    @Headers(value = ["Accept: application/json"])
    suspend fun loadRecentBuildsForProjectBranch(
        @Path(value = "vcsType") vcsType: String,
        @Path(value = "username") username: String,
        @Path(value = "project") project: String,
        @Path(value = "branch") branch: String,
        @Query(value = "circle-token") circleToken: String
    ): Response<List<Build>>

    @GET(value = "project/{vcsType}/{username}/{project}/{buildNum}")
    @Headers(value = ["Accept: application/json"])
    suspend fun getSingleJob(
        @Path(value = "vcsType") vcsType: String,
        @Path(value = "username") username: String,
        @Path(value = "project") project: String,
        @Path(value = "buildNum") buildNum: String,
        @Query(value = "circle-token") circleToken: String
    ): Response<Build>

//    @GET("project/{vcsType}/{username}/{project}/{buildNum}/artifacts")
//    @Headers("Accept: application/json")
//    fun listArtifacts(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("buildNum") buildNum: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @GET("project/{vcsType}/{username}/{project}/latest/artifacts")
//    @Headers("Accept: application/json")
//    fun listLastestArtifacts(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("buildNum") buildNum: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @POST("project/{vcsType}/{username}/{project}/{buildNum}/retry")
//    @Headers("Accept: application/json")
//    fun retry(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("buildNum") buildNum: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @POST("project/{vcsType}/{username}/{project}/{buildNum}/ssh-users")
//    @Headers("Accept: application/json")
//    fun addUser(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("buildNum") buildNum: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @POST("project/{vcsType}/{username}/{project}/{buildNum}/cancel")
//    @Headers("Accept: application/json")
//    fun cancel(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("buildNum") buildNum: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @DELETE("project/{vcsType}/{username}/{project}/build-cache")
//    @Headers("Accept: application/json")
//    fun clearCache(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @GET("project/{vcsType}/{username}/{project}/envvar")
//    @Headers("Accept: application/json")
//    fun listEnvVar(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @GET("project/{vcsType}/{username}/{project}/envvar/{name}")
//    @Headers("Accept: application/json")
//    fun getSingleEnvVar(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("name") name: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @DELETE("project/{vcsType}/{username}/{project}/envvar/{name}")
//    @Headers("Accept: application/json")
//    fun deleteEnvVar(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("name") name: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @GET("project/{vcsType}/{username}/{project}/checkout-key")
//    @Headers("Accept: application/json")
//    fun listCheckoutKeys(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @GET("project/{vcsType}/{username}/{project}/checkout-key/{fingerprint}")
//    @Headers("Accept: application/json")
//    fun getCheckoutKey(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("fingerprint") fingerprint: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @DELETE("project/{vcsType}/{username}/{project}/checkout-key/{fingerprint}")
//    @Headers("Accept: application/json")
//    fun deleteCheckoutKey(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("fingerprint") fingerprint: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>
//
//    @GET("project/{vcsType}/{username}/{project}/{buildNum}/tests")
//    @Headers("Accept: application/json")
//    fun listTestMetadata(
//        @Path("vcsType") vcsType: String,
//        @Path("username") username: String,
//        @Path("project") project: String,
//        @Path("buildNum") buildNum: String,
//        @Query("circle-token") circleToken: String
//    ): Response<Retry>

    companion object {
        const val ENDPOINT = "https://circleci.com/"
    }
}
