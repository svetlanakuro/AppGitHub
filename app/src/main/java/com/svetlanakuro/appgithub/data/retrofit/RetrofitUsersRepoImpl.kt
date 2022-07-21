package com.svetlanakuro.appgithub.data.retrofit

import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.*
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUsersRepoImpl : UsersRepo {

    private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()
    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)

    override fun getUsers(onSuccess: (List<GitUserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getListUsers()
            .subscribeBy(onSuccess = { onSuccess.invoke(it) }, onError = { onError?.invoke(it) })
    }

    override fun getUsers(): Single<List<GitUserEntity>> = api.getListUsers()

    override fun getProjectsUser(
        login: String, onSuccess: (List<GitProjectsEntity>) -> Unit, onError: ((Throwable) -> Unit)?
    ) {
        api.getListUserProjects(login)
            .subscribeBy(onSuccess = { onSuccess.invoke(it) }, onError = { onError?.invoke(it) })
    }

    override fun getProjectsUser(login: String): Single<List<GitProjectsEntity>> =
        api.getListUserProjects(login)

}