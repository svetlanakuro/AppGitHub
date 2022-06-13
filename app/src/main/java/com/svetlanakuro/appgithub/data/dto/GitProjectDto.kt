package com.svetlanakuro.appgithub.data.dto

import com.google.gson.annotations.SerializedName

data class GitProjectDto(
    val id: Int,
    @SerializedName("name")
    val nameProject: String,
    @SerializedName("description")
    val descriptionProject: String?
)