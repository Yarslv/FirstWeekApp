package com.internship.firstweekapp.ui.settingFragment

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

class SettingFragmentViewModel : BaseViewModel() {
    var playerIsCross = true
    var playerIsFirst = true

    var isStartGameEvent = SingleLiveEvent<Setting>()


    fun onClick() {
        isStartGameEvent.postValue(Setting(playerIsCross, playerIsFirst, true))
    }

}

data class Setting(
    var isCross: Boolean,
    var isFirst: Boolean,
    var isStart: Boolean
)