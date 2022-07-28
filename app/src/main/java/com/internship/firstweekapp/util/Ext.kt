package com.internship.firstweekapp.util

import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.R

fun Fragment.navigate(dir: NavDirections) {
    findNavController().navigate(dir)

}

fun Fragment.navigateBack() {
    findNavController().navigateUp()
}

@BindingAdapter("android:setCheckedButton")
fun RadioGroup.setCheckedButton(noteColor: NotesColor) {
    when (noteColor) {
        NotesColor.Red -> {check(R.id.radio1)}
        NotesColor.Yellow -> {check(R.id.radio2)}
        NotesColor.Green -> {check(R.id.radio3)}
        NotesColor.Pink -> {check(R.id.radio4)}
        NotesColor.Cyan -> {check(R.id.radio5)}
        NotesColor.Turquoise -> {check(R.id.radio6)}
            }
}

@InverseBindingAdapter(attribute = "android:setCheckedButton")
fun getRadioGroupValue(radioGroup: RadioGroup): NotesColor {
    return when (radioGroup.checkedRadioButtonId) {
        R.id.radio1 -> NotesColor.Red
        R.id.radio2 -> NotesColor.Yellow
        R.id.radio3 -> NotesColor.Green
        R.id.radio4 -> NotesColor.Pink
        R.id.radio5 -> NotesColor.Cyan
        R.id.radio6 -> NotesColor.Turquoise
        else -> {
            NotesColor.Red}
    }
}

@BindingAdapter("android:setCheckedButtonAttrChanged")
fun setRadioGroupListeners(
    radioGroup: RadioGroup,
    attrChange: InverseBindingListener
) {
    radioGroup.setOnCheckedChangeListener { group, checkedId ->
        attrChange.onChange()
    }
}