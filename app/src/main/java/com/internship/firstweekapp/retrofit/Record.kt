package com.internship.firstweekapp.retrofit

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Record(
    @SerializedName("id")
    val id: Int,
    val gen: String,
    val sp: String,
    val ssp: String,
    val en: String,
    val rec: String,
    val cnt: String,
    val loc: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String,
    @SerializedName("alt")
    val alt: String,
    val type: String,
    val url: String,
    val file: String,
    @SerializedName("file-name")
    val file_name: String,
    @Contextual val sono: Sono,
    val lic: String,
    val q: String,
    val length: String,
    val time: String,
    val date: String,
    val uploaded: String,
    val also: Array<String>,
    @SerializedName("rmk")
    val rmk: String,
    @SerializedName("bird-seen")
    val bird_seen: String,
    @SerializedName("playback-used")
    val playback_used: String,
)