package com.internship.firstweekapp.retrofit

import com.google.gson.annotations.SerializedName

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
    val rank: Int,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("topText")
    val topText: String
)