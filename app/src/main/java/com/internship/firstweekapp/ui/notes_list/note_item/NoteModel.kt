package com.internship.firstweekapp.ui.notes_list.note_item

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.adapter.AdapterContentElement
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.util.NotesColor

class NoteModel(
    title: String = "",
    content: String = "", color: NotesColor, val id: Int = -1, val isEditable: Boolean = false
) {
    var title = ObservableField(title)
    var content = ObservableField(content)
    var isExtended = ObservableField(false)
    var color = ObservableField(color)
    val navEvent = SingleLiveEvent<Boolean>()

    fun showMoreLessClick() {
        isExtended.set(isExtended.get()!!.not())
    }

    fun onEdit() {
        navEvent.postValue(true)
    }
}

