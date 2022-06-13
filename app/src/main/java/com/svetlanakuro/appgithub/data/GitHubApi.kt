package com.svetlanakuro.appgithub.data

import com.svetlanakuro.appgithub.data.dto.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface GitHubApi {

    @GET("users")
    fun getListUsers(): List<GitUserDto>

    @GET("users/{user}")
    fun getUserDetail(
        @Path("user")
        user: String
    ): GitUserDto

    @GET("users/{user}/repos")
    fun getListUserProjects(
        @Path("user")
        user: String
    ): List<GitProjectDto>
}