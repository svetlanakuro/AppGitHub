package com.svetlanakuro.appgithub.ui.userslist

import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.GitUserEntity

class UsersPresenter(
    private val usersRepo: UsersRepo
) : UsersContract.Presenter {

    private var view: UsersContract.View? = null

    private var usersList: List<GitUserEntity>? = null
    private var inProgress: Boolean = false

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        usersList?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        inProgress = true
        usersRepo.getUsers(onSuccess = {
            view?.showProgress(false)
            view?.showUsers(it)
            usersList = it
            inProgress = false
        }, onError = {
            view?.showProgress(false)
            view?.showError(it)
            inProgress = false
        })
    }
}