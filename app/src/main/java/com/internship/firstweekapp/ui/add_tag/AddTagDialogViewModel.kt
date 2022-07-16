package com.internship.firstweekapp.ui.add_tag

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.databinding.ObservableField
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

enum class ButtonClick {
    Cancel, Save
}

class AddTagDialogViewModel : BaseViewModel() {

    val text = ObservableField("")
    val singleLiveEvent = SingleLiveEvent<ButtonClick>()


    fun onCancel(view: View, editText: TextInputEditText) {
        (view as MaterialButton).changeColor(Color.WHITE, Color.parseColor("#5b67ca"))
        editText.clearFocus()
        singleLiveEvent.postValue(ButtonClick.Cancel)
    }

    fun onSave(view: View, editText: TextInputEditText) {
        (view as MaterialButton).changeColor(Color.WHITE, Color.parseColor("#5b67ca"))
        editText.clearFocus()
        singleLiveEvent.postValue(ButtonClick.Save)
    }

    private fun MaterialButton.changeColor(textColor: Int, bgColor: Int) {
        setTextColor(textColor)
        backgroundTintList = ColorStateList.valueOf(bgColor)
    }

}