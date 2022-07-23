package com.internship.firstweekapp.ui.tabs.setting_fragment

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.subarch.ScreenModel
import com.internship.firstweekapp.ui.setup_screens.confirm_class.ConfirmClassFragmentViewModel
import com.internship.firstweekapp.ui.setup_screens.confirm_class.UserClass
import com.internship.firstweekapp.ui.setup_screens.confirm_level.ConfirmLevelFragmentViewModel

class SettingFragmentViewModel(
    var sliderModel: ScreenModel<Float>,
    val radioModel: ScreenModel<UserClass>
) : BaseViewModel() {


    init {
        sliderModel.setName(ConfirmLevelFragmentViewModel::class.java)
        sliderModel.value.set(1f)
        sliderModel.readModel()
        radioModel.setName(ConfirmClassFragmentViewModel::class.java)
        radioModel.value.set(UserClass.None)
        radioModel.readModel()
    }

    fun save() {
        sliderModel.saveModel()
        radioModel.saveModel()
    }
}