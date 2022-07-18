package com.svetlanakuro.appgithub.data.retrofit

import com.svetlanakuro.appgithub.domain.entities.*
import io.reactivex.rxjava3.core.*
import retrofit2.Call
import retrofit2.http.*

interface GitHubApi {

    @GET("users")
    fun getListUsers(): Single<List<GitUserEntity>>

    @GET("users/{user}")
    fun getUserDetail(
        @Path("user")
        user: String
    ): Call<GitUserEntity>

    @GET("users/{user}/repos")
    fun getListUserProjects(
        @Path("user")
        user: String
    ): Single<List<GitProjectsEntity>>
}