package com.internship.firstweekapp.ui.notes_list.note_item

import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.R
import com.internship.firstweekapp.ui.notes_list.note_adapter.NotesRecyclerAdapter
import com.internship.firstweekapp.util.NotesColor

@BindingAdapter("android:setColor")
fun MaterialTextView.setColor(color: NotesColor) {
    setTextColor(
        when (color) {
            NotesColor.Red -> {
                context.getColor(R.color.notes_color_red_dark)
            }
            NotesColor.Yellow -> {
                context.getColor(R.color.notes_color_yellow)
            }
            NotesColor.Green -> {
                context.getColor(R.color.notes_color_green_light)
            }
            NotesColor.Pink -> {
                context.getColor(R.color.notes_color_pink_light)
            }
            NotesColor.Cyan -> {
                context.getColor(R.color.notes_color_cyan)
            }
            NotesColor.Turquoise -> {
                context.getColor(R.color.notes_color_turquoise)
            }
        }
    )
}

@BindingAdapter("android:setStocked")
fun MaterialTextView.setStocked(isExtended: Boolean) {
    height = if (isExtended) {
        100
    } else {
        0
    }

}

@BindingAdapter("android:setImage")
fun AppCompatImageView.setImage(isExtended: Boolean) {
    if (isExtended) {
        setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_baseline_expand_less_24))
    } else {
        setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_baseline_expand_more_24))
    }
}

@BindingAdapter("android:setList")
fun RecyclerView.setList(list: ArrayList<NoteViewModel>) {
    (adapter as NotesRecyclerAdapter).setContent(list)
}