package com.svetlanakuro.appgithub.ui.userslist

import androidx.lifecycle.LiveData
import com.svetlanakuro.appgithub.domain.entities.GitUserEntity

interface UsersContract {

    interface ViewModel {

        val usersLiveData: LiveData<List<GitUserEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>

        fun onRefresh()
    }

}