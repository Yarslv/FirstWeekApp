package com.internship.firstweekapp.ui.tabs.setting

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.ui.use_cases.ChangeThemeUseCase
import com.internship.firstweekapp.ui.use_cases.GetDefaultLanguageUseCase
import com.internship.firstweekapp.ui.use_cases.IsNightThemeUseCase

class SettingFragmentViewModel(
    isNightThemeUseCase: IsNightThemeUseCase,
    getDefaultLanguageUseCase: GetDefaultLanguageUseCase,
    private val changeThemeUseCase: ChangeThemeUseCase
) : BaseViewModel() {

    val navigateEvent = SingleLiveEvent<Boolean>()

    private val nightModeObservableBoolean = ObservableBoolean(isNightThemeUseCase.invoke())

    var nightMode: Boolean
        get() = nightModeObservableBoolean.get()
        set(value) {
            nightModeObservableBoolean.set(value)
            changeThemeUseCase(value)
        }

    val currentLang = ObservableField(getDefaultLanguageUseCase.invoke())


    fun navigateToLanguageFragment() {
        navigateEvent.postValue(true)
    }

}


