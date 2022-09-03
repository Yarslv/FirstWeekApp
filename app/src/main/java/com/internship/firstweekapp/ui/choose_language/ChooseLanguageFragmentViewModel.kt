package com.internship.firstweekapp.ui.choose_language

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.ui.use_cases.GetDefaultLanguageUseCase
import com.internship.firstweekapp.ui.tabs.setting.Languages

class ChooseLanguageFragmentViewModel(getDefaultLanguageUseCase: GetDefaultLanguageUseCase) :
    BaseViewModel() {

    private val languageObservableField = ObservableField(getDefaultLanguageUseCase())
    val changeLanguageEvent = SingleLiveEvent<Languages>()

    var language: Languages
        get() = languageObservableField.get()!!
        set(value) {
            languageObservableField.set(value)
            changeLanguageEvent.postValue(value)
        }
}