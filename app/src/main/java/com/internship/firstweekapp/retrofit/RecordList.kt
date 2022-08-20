package com.internship.firstweekapp.retrofit

import com.google.gson.annotations.SerializedName

data class RecordList(
    @SerializedName("numRecordings")
    val numRecordings: Int,
    @SerializedName("numSpecies")
    val numSpecies: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("numPages")
    val numPages: Int,
    @SerializedName("recordings")
    val recordings: Array<Record>
)

