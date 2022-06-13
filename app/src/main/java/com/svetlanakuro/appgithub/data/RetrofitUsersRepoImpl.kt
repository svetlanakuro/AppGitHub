package com.svetlanakuro.appgithub.data.database

import com.svetlanakuro.appgithub.data.GitHubApi
import com.svetlanakuro.appgithub.data.dto.*
import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUsersRepoImpl() : UsersRepo {

    private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)

    override fun getUsers(onSuccess: (List<GitUserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        onSuccess(convertUserDtoToEntity(api.getListUsers()))
    }

//    override fun getUsersList(): Single<List<GitUserEntity>> {
//        return api.getListUsers().map { users ->
//            convertUserDtoToEntity(users)
//        }
//    }
//
//    override fun getProjectsUser(login: String): Single<List<GitProjectsEntity>> {
//        return api.getListUserProjects(login).map { projects ->
//            convertProjectDtoToEntity(projects)
//        }
//    }

    private fun convertUserDtoToEntity(usersDto: List<GitUserDto>): List<GitUserEntity> {
        val listUsersEntity = mutableListOf<GitUserEntity>()
        usersDto.forEach { userDto ->
            listUsersEntity.add(
                GitUserEntity(
                    id = userDto.id,
                    login = userDto.login,
                    name = "userDto.name",
                    avatarUrl = userDto.avatarUrl,
                    publicRepos = 0,
                    followers = 0,
                    following = 0,
                )
            )
        }
        return listUsersEntity
    }

    private fun convertProjectDtoToEntity(projects: List<GitProjectDto>): List<GitProjectsEntity> {
        val listProjectsEntity = mutableListOf<GitProjectsEntity>()
        projects.forEach { projectDto ->
            listProjectsEntity.add(
                GitProjectsEntity(
                    id = projectDto.id,
                    nameProject = projectDto.nameProject,
                    descriptionProject = projectDto.descriptionProject
                )
            )
        }
        return listProjectsEntity
    }
}