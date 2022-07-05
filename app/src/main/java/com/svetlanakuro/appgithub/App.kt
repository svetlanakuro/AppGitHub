package com.svetlanakuro.appgithub

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.svetlanakuro.appgithub.data.MockUsersRepoImpl
import com.svetlanakuro.appgithub.domain.UsersRepo

class App : Application() {

    val usersRepo: UsersRepo by lazy { MockUsersRepoImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App