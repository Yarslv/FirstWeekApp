package com.internship.firstweekapp.ui.tabs.points_fragment

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.ui.setup_screens.confirm_class.UserClass

class PointModel(
    val classType: UserClass,
    val level: String,
    val latitude: Double,
    val dateTime: Long,
    val longtitude: Double
) : BaseViewModel()