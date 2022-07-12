package com.svetlanakuro.appgithub.data.retrofit

import com.svetlanakuro.appgithub.domain.entities.*
import retrofit2.Call
import retrofit2.http.*

interface GitHubApi {

    @GET("users")
    fun getListUsers(): Call<List<GitUserEntity>>

    @GET("users/{user}")
    fun getUserDetail(
        @Path("user")
        user: String
    ): Call<GitUserEntity>

    @GET("users/{user}/repos")
    fun getListUserProjects(
        @Path("user")
        user: String
    ): Call<List<GitProjectsEntity>>
}