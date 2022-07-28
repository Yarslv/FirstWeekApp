package com.internship.firstweekapp.ui.add_note

import android.util.Log
import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.room.DatabaseProvider
import com.internship.firstweekapp.util.NotesColor
import androidx.lifecycle.viewModelScope
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.room.Note
import com.internship.firstweekapp.util.AddOrEditFlag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



enum class Error {
    TitleIsEmpty, ContentIsEmpty
}
class AddEditNoteFragmentViewModel(private val databaseProvider: DatabaseProvider) : BaseViewModel() {
    val isAddOrEdit = ObservableField<AddOrEditFlag>()
    var id = -1

    val title = ObservableField<String>()
    val content = ObservableField<String>()

    val color = ObservableField(NotesColor.Red)

    val ediIsEnable = ObservableField(false)

    val toastEvent = SingleLiveEvent<Error>()

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (isAddOrEdit.get() == AddOrEditFlag.Edit) {
                val old = databaseProvider.getConcrete(id)
                title.set(old.title)
                content.set(old.content)
            }
        }
    }

    fun save():Boolean {
        if(title.get().isNullOrEmpty()){
            toastEvent.postValue(Error.TitleIsEmpty)
            return false
        }

        if(content.get().isNullOrEmpty()){
            toastEvent.postValue(Error.ContentIsEmpty)
            return false
        }
        viewModelScope.launch(Dispatchers.IO) {

            when (isAddOrEdit.get()) {

                AddOrEditFlag.Add -> {
                    databaseProvider.add(
                        Note(
                            title = title.get().toString(),
                            content = content.get().toString(),
                            color = color.get().toString(),
                            isEdit = ediIsEnable.get() == true
                        )
                    )

                }
                AddOrEditFlag.Edit -> {
                    Log.d("recwEdit", "d;lfd;")
                    databaseProvider.update(
                        Note(
                            title = title.get().toString(),
                            content = content.get().toString(),
                            color = color.get().toString(),
                            isEdit = true,
                            id = id
                        )
                    )
                }
                null -> {}
            }
        }
        return true
    }

}