package com.internship.firstweekapp.retrofit

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MemeMakerResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Array<Meme>,
    @SerializedName("message")
    val message: String,
    @SerializedName("next")
    val next: String
)
