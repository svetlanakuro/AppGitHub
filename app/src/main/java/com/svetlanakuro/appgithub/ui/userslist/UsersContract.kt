package com.svetlanakuro.appgithub.ui.userslist

import com.svetlanakuro.appgithub.domain.entities.GitUserEntity

interface UsersContract {

    interface View {

        fun showUsers(users: List<GitUserEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }

    interface Presenter {

        fun attach(view: View)
        fun detach()

        fun onRefresh()
    }

}