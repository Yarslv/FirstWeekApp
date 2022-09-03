package com.internship.firstweekapp.retrofit


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Condition(
    @SerializedName("code")
    val code: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text: String
)