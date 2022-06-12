package com.svetlanakuro.appgithub.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitUserEntity(
    val id: Int,
    val login: String,
    val name: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("public_repos")
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
    val projectsList: List<GitProjectsEntity>
) : Parcelable