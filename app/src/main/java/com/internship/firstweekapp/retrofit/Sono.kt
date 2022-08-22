package com.internship.firstweekapp.retrofit

import kotlinx.serialization.Serializable

@Serializable
data class Sono(
    val small: String,
    val med: String,
    val large: String,
    val full: String
)