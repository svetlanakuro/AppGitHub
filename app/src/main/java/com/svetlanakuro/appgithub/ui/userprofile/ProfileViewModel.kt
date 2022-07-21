package com.svetlanakuro.appgithub.ui.userprofile

import com.svetlanakuro.appgithub.domain.UsersRepo
import com.svetlanakuro.appgithub.domain.entities.GitProjectsEntity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.*

class ProfileViewModel(
    private val usersRepo: UsersRepo
) : ProfileContract.ViewModel {

    override val profileLiveData: Observable<List<GitProjectsEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()

    override fun onRefresh(login: String) {
        progressLiveData
        usersRepo.getProjectsUser(login).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
            onSuccess = {
            progressLiveData.mutable().onNext(false)
            profileLiveData.mutable().onNext(it)
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