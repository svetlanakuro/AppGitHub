package com.svetlanakuro.appgithub.ui.userprofile

import com.svetlanakuro.appgithub.domain.entities.GitProjectsEntity

interface ProfileContract {

    interface View {

        fun showProfile(userProjects: List<GitProjectsEntity>)
        fun showError(throwable: Throwable)
        fun showProgress(inProgress: Boolean)
    }

    interface Presenter {

        fun attach(view: View)
        fun detach()

        fun onRefresh(login: String)
    }

}