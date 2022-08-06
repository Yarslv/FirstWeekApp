package com.internship.firstweekapp.ui.add_card

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.util.enums.Error
import com.internship.firstweekapp.util.enums.SensorSubtype
import com.internship.firstweekapp.util.enums.SensorType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddCardFragmentViewModel : BaseViewModel() {

    val model = AddCardFragmentModel()

    val type = ObservableField<SensorType>()
    val subtype = ObservableField<SensorSubtype>()
    val roomName = ObservableField("")


    val navEvent = SingleLiveEvent<Boolean>()
    val hideKeyboardEvent = SingleLiveEvent<Boolean>()

    fun navigate() {
        if (check())
            navEvent.postValue(true)
    }

    fun hideKeyboard(){
        hideKeyboardEvent.postValue(true)
    }

    private fun check(): Boolean {
        var result = true
        if (roomName.get().toString().isEmpty()) {
            model.nameError.set(Error.EmptyTextFiled)
            result = false
        }
        if (type.get() == null
        ) {
            model.typeError.set(Error.EmptyTypeDropDownField)
            result = false
        }
        if (subtype.get() == null
        ) {
            model.subtypeError.set(Error.EmptySubtypeDropDownField)
            result = false
        }
        hideError()


        return result
    }

    private fun hideError() {
        viewModelScope.launch {
            delay(1500)
            model.nameError.set(Error.NoError)
            model.typeError.set(Error.NoError)
            model.subtypeError.set(Error.NoError)
        }
    }


}

