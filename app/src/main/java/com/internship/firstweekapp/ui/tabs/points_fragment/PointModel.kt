package com.internship.firstweekapp.ui.tabs.points_fragment

import com.internship.firstweekapp.arch.BaseViewModel

class PointModel(
    val classType: String,
    val level: String,
    val latitude: Double, val longtitude: Double
) : BaseViewModel()