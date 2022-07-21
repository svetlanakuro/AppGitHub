package com.svetlanakuro.appgithub.domain

import com.svetlanakuro.appgithub.domain.entities.*
import io.reactivex.rxjava3.core.Single

interface UsersRepo {

    fun getUsers(
        onSuccess: (List<GitUserEntity>) -> Unit, onError: ((Throwable) -> Unit)? = null
    )

    fun getUsers(): Single<List<GitUserEntity>>

    fun getProjectsUser(
        login: String, onSuccess: (List<GitProjectsEntity>) -> Unit, onError: ((Throwable) -> Unit)? = null
    )

    fun getProjectsUser(login: String): Single<List<GitProjectsEntity>>
}