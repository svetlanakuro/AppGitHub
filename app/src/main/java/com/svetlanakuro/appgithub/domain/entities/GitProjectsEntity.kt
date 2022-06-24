package com.svetlanakuro.appgithub.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitProjectsEntity(
    val id: Int,
    @SerializedName("name")
    val nameProject: String,
    @SerializedName("description")
    val descriptionProject: String?
) : Parcelable