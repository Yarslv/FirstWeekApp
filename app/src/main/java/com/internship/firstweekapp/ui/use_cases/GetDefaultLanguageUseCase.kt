package com.internship.firstweekapp.ui.use_cases

import com.internship.firstweekapp.ui.tabs.setting.Languages
import java.util.*

object GetDefaultLanguageUseCase {
    operator fun invoke(): Languages {
        return when (Locale.getDefault().toString()) {
            Languages.UK.lang_label -> {
                Languages.UK
            }
            else -> Languages.EN
        }
    }
}