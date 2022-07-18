package com.svetlanakuro.appgithub.ui.userprofile

import androidx.lifecycle.LiveData
import com.svetlanakuro.appgithub.domain.entities.GitProjectsEntity
import io.reactivex.rxjava3.core.Observable

interface ProfileContract {

    interface ViewModel {

        val profileLiveData: Observable<List<GitProjectsEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>

        fun onRefresh(login: String)
    }

}