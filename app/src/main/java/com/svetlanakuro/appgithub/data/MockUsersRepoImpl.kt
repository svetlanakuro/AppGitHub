package com.svetlanakuro.appgithub.data

import android.os.*
import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.*

private const val DATA_LOADING_FAKE_DELAY = 2_000L

class MockUsersRepoImpl : UsersRepo {

    private val mockListUsers = listOf(
        GitUserEntity(
            id = 0,
            login = "svetlanakuro",
            name = "Svetlana",
            avatarUrl = "https://avatars.githubusercontent.com/u/84097209?v=4",
            publicRepos = 7,
            followers = 1,
            following = 4,
            projectsList = listOf(
                GitProjectsEntity(
                    0, "AppGitHub", "GitHub client with list of users, profiles, repositories."
                ),
                GitProjectsEntity(
                    1, "MVP_MVVM_Patterns", "Presentation Layer Decomposition Patterns"
                ),
                GitProjectsEntity(
                    2, "PictureOfTheDay", "Picture Of The Day App"
                ),
            )
        ),
        GitUserEntity(
            id = 1,
            login = "user1",
            name = "User",
            avatarUrl = "https://avatars.githubusercontent.com",
            publicRepos = 3,
            followers = 0,
            following = 0,
            projectsList = listOf(
                GitProjectsEntity(
                    0, "Project", "Description"
                ),
                GitProjectsEntity(
                    1, "Project", null
                ),
                GitProjectsEntity(
                    2, "Project", "Description"
                ),
            )

        ),
        GitUserEntity(
            id = 2,
            login = "user2",
            name = "User",
            avatarUrl = "https://avatars.githubusercontent.com",
            publicRepos = 3,
            followers = 0,
            following = 0,
            projectsList = listOf(
                GitProjectsEntity(
                    0, "Project", "Description"
                ),
                GitProjectsEntity(
                    1, "Project", "Description"
                ),
                GitProjectsEntity(
                    2, "Project", null
                ),
            )
        ),
    )

    override fun getUsers(onSuccess: (List<GitUserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(mockListUsers)
        }, DATA_LOADING_FAKE_DELAY)
    }

    override fun getProjectsUser(
        login: String,
        onSuccess: (List<GitProjectsEntity>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        var userProjects: List<GitProjectsEntity> = emptyList()
        mockListUsers.forEach { user ->
            if (user.login == login) {
                userProjects = user.projectsList!!
            }
        }
        Handler(Looper.getMainLooper()).postDelayed({
            onSuccess(userProjects)
        }, DATA_LOADING_FAKE_DELAY)
    }

}