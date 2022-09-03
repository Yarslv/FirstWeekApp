package com.internship.firstweekapp.ui.use_cases

import androidx.appcompat.app.AppCompatDelegate

object IsNightThemeUseCase {

    operator fun invoke(): Boolean = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
}