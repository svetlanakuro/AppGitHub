package com.svetlanakuro.appgithub.ui.userprofile

import androidx.lifecycle.*
import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.GitProjectsEntity

class ProfileViewModel(
    private val usersRepo: UsersRepo
) : ProfileContract.ViewModel {

    override val profileLiveData: LiveData<List<GitProjectsEntity>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = MutableLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()

    override fun onRefresh(login: String) {
        progressLiveData.mutable().postValue(true)
        usersRepo.getProjectsUser(login, onSuccess = {
            progressLiveData.mutable().postValue(false)
            profileLiveData.mutable().postValue(it)
        }, onError = {
            progressLiveData.mutable().postValue(false)
            errorLiveData.mutable().postValue(it)
        })
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T> ?: throw IllegalStateException("This is not MutableLiveData")
    }
}