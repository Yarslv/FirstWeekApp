package com.internship.firstweekapp.util

import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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

@BindingAdapter("android:setColor")
fun ConstraintLayout.setColor(color: NotesColor){
    background = when(color){
        NotesColor.Red -> ContextCompat.getDrawable(context, R.color.notes_color_red_dark)
        NotesColor.Yellow -> ContextCompat.getDrawable(context, R.color.notes_color_yellow)
        NotesColor.Green -> ContextCompat.getDrawable(context, R.color.notes_color_green_light)
        NotesColor.Pink -> ContextCompat.getDrawable(context, R.color.notes_color_pink_light)
        NotesColor.Cyan -> ContextCompat.getDrawable(context, R.color.notes_color_cyan)
        NotesColor.Turquoise -> ContextCompat.getDrawable(context, R.color.notes_color_turquoise)
    }
}
