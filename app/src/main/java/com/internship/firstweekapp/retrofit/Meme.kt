package com.internship.firstweekapp.retrofit

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Meme(
    @SerializedName("ID")
    val id: Int,
    @SerializedName("bottomText")
    val bottomText: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rank")
    val rank: Int = -1,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("topText")
    val topText: String
)