package com.svetlanakuro.appgithub.ui.userslist

import com.svetlanakuro.appgithub.domain.entities.GitUserEntity
import io.reactivex.rxjava3.core.Observable

interface UsersContract {

    interface ViewModel {

        val usersLiveData: Observable<List<GitUserEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>

        fun onRefresh()
    }

}