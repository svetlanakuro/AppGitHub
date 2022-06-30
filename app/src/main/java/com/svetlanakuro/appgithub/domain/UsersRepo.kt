package com.svetlanakuro.appgithub.domain

import com.svetlanakuro.appgithub.domain.entities.*

interface UsersRepo {

    fun getUsers(
        onSuccess: (List<GitUserEntity>) -> Unit, onError: ((Throwable) -> Unit)? = null
    )

    fun getProjectsUser(
        login: String, onSuccess: (List<GitProjectsEntity>) -> Unit, onError: ((Throwable) -> Unit)? = null
    )
}