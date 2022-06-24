package com.svetlanakuro.appgithub.domain

import com.svetlanakuro.appgithub.domain.entities.*

interface UsersRepo {

    fun getUsers(
        onSuccess: (List<GitUserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )

//    fun getUsersList(): List<GitUserEntity>
//    fun getProjectsUser(login: String): List<GitProjectsEntity>
}