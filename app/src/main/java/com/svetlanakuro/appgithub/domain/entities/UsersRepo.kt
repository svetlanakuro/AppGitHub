package com.svetlanakuro.appgithub.domain.entities

interface UsersRepo {

    fun getUsersList(): List<GitUserEntity>
    fun getProjectsUser(login: String): List<GitProjectsEntity>
}