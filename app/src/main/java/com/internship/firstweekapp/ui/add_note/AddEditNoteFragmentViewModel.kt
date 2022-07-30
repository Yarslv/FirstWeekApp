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
    val model = ADDEditModel()

    val toastEvent = SingleLiveEvent<Error>()

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (isAddOrEdit.get() == AddOrEditFlag.Edit) {
                val old = databaseProvider.getConcrete(model.id)
                model.title.set(old.title)
                model.content.set(old.content)
            }
        }
    }

    fun save():Boolean {
        if(model.title.get().isNullOrEmpty()){
            toastEvent.postValue(Error.TitleIsEmpty)
            return false
        }

        if(model.content.get().isNullOrEmpty()){
            toastEvent.postValue(Error.ContentIsEmpty)
            return false
        }
        viewModelScope.launch(Dispatchers.IO) {

            when (isAddOrEdit.get()) {

                AddOrEditFlag.Add -> {
                    databaseProvider.add(
                        Note(
                            title = model.title.get().toString(),
                            content = model.content.get().toString(),
                            color = model.color.get().toString(),
                            isEdit = model.ediIsEnable.get() == true
                        )
                    )

                }
                AddOrEditFlag.Edit -> {
                    Log.d("recwEdit", "d;lfd;")
                    databaseProvider.update(
                        Note(
                            title = model.title.get().toString(),
                            content = model.content.get().toString(),
                            color = model.color.get().toString(),
                            isEdit = true,
                            id = model.id
                        )
                    )
                }
                null -> {}
            }
        }
        return true
    }

}
class ADDEditModel(){
    var id = -1

    val title = ObservableField<String>()
    val content = ObservableField<String>()

    val color = ObservableField(NotesColor.Red)

    val ediIsEnable = ObservableField(false)

}