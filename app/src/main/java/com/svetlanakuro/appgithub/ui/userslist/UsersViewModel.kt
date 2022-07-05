package com.svetlanakuro.appgithub.ui.userslist

import androidx.lifecycle.*
import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.GitUserEntity

class UsersViewModel(
    private val usersRepo: UsersRepo
) : UsersContract.ViewModel {

    override val usersLiveData: LiveData<List<GitUserEntity>> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = MutableLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()

    override fun onRefresh() {
        progressLiveData.mutable().postValue(true)
        usersRepo.getUsers(onSuccess = {
            progressLiveData.mutable().postValue(false)
            usersLiveData.mutable().postValue(it)
        }, onError = {
            progressLiveData.mutable().postValue(false)
            errorLiveData.mutable().postValue(it)
        })
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T> ?: throw IllegalStateException("This is not MutableLiveData")
    }
}