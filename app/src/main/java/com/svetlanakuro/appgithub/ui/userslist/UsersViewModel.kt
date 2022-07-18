package com.svetlanakuro.appgithub.ui.userslist

import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.GitUserEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.*

class UsersViewModel(
    private val usersRepo: UsersRepo
) : UsersContract.ViewModel {

    override val usersLiveData: Observable<List<GitUserEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()

    override fun onRefresh() {
        progressLiveData
        usersRepo.getUsers().observeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onSuccess = {
                progressLiveData.mutable().onNext(false)
                usersLiveData.mutable().onNext(it)
            }, onError = {
                progressLiveData.mutable().onNext(false)
                errorLiveData.mutable().onNext(it)
            }
        )
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T> ?: throw IllegalStateException("This is not MutableLiveData")
    }
}