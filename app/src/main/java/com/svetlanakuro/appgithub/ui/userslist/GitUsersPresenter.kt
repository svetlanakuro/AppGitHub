package com.svetlanakuro.appgithub.ui.userslist

import com.svetlanakuro.appgithub.domain.UsersRepo

class GitUsersPresenter(
    private val usersRepo: UsersRepo
) : GitUsersContract.Presenter {

    private var view: GitUsersContract.View? = null

    override fun attach(view: GitUsersContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        usersRepo.getUsers(onSuccess = {
            view?.showProgress(false)
            view?.showUsers(it)
        }, onError = {
            view?.showProgress(false)
            view?.showError(it)
        })
    }
}