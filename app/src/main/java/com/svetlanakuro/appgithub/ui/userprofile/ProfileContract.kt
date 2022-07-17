package com.svetlanakuro.appgithub.ui.userprofile

import androidx.lifecycle.LiveData
import com.svetlanakuro.appgithub.domain.entities.GitProjectsEntity

interface ProfileContract {

    interface ViewModel {

        val profileLiveData: LiveData<List<GitProjectsEntity>>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>

        fun onRefresh(login: String)
    }

}