package com.svetlanakuro.appgithub.ui.userprofile

import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.GitProjectsEntity

class ProfilePresenter(
    private val usersRepo: UsersRepo
) : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null

    private var userProjectsList: List<GitProjectsEntity>? = null
    private var inProgress: Boolean = false

    override fun attach(view: ProfileContract.View) {
        this.view = view
        view.showProgress(inProgress)
        userProjectsList?.let { view.showProfile(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh(login: String) {
        inProgress = true
        usersRepo.getProjectsUser(login, onSuccess = {
            view?.showProgress(false)
            view?.showProfile(it)
            userProjectsList = it
            inProgress = false
        }, onError = {
            view?.showProgress(false)
            view?.showError(it)
            inProgress = false
        })
    }
}