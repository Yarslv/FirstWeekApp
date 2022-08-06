package com.internship.firstweekapp.ui.add_card

import androidx.databinding.ObservableField
import com.internship.firstweekapp.util.enums.Error

class AddCardFragmentModel() {
    val nameError = ObservableField(Error.NoError)
    val typeError = ObservableField(Error.NoError)
    val subtypeError = ObservableField(Error.NoError)
}