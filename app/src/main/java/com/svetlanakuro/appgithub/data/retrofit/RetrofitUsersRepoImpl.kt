package com.svetlanakuro.appgithub.data.retrofit

import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUsersRepoImpl : UsersRepo {

    private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)

    override fun getUsers(onSuccess: (List<GitUserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getListUsers().enqueue(object : Callback<List<GitUserEntity>> {
            override fun onResponse(
                call: Call<List<GitUserEntity>>, response: Response<List<GitUserEntity>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException("Error getting data."))
                }
            }

            override fun onFailure(call: Call<List<GitUserEntity>>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }

    override fun getProjectsUser(
        login: String, onSuccess: (List<GitProjectsEntity>) -> Unit, onError: ((Throwable) -> Unit)?
    ) {
        api.getListUserProjects(login).enqueue(object : Callback<List<GitProjectsEntity>> {
            override fun onResponse(
                call: Call<List<GitProjectsEntity>>, response: Response<List<GitProjectsEntity>>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException("Error getting data."))
                }
            }

            override fun onFailure(call: Call<List<GitProjectsEntity>>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }

}