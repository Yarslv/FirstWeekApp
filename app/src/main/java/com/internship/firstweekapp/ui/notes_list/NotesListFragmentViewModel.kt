package com.internship.firstweekapp.ui.notes_list

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.room.DatabaseProvider
import com.internship.firstweekapp.room.Note
import com.internship.firstweekapp.ui.notes_list.note_item.NoteModel
import com.internship.firstweekapp.util.Direction
import com.internship.firstweekapp.util.SortBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesListFragmentViewModel(val databaseProvider: DatabaseProvider) : BaseViewModel() {

    val list = ObservableArrayList<NoteModel>()
    val sortBy = MutableLiveData<SortBy>()
    private val sortTypeObserver = Observer<SortBy> {
        viewModelScope.launch(Dispatchers.IO) {}
    }

    val direction = MutableLiveData<Direction>()

    val navigationEvent = SingleLiveEvent<Boolean>()

    private val directionObserver = Observer<Direction> {
        viewModelScope.launch(Dispatchers.IO) {
            list.clear()
            setList()
        }
    }

    init {
        direction.observeForever(directionObserver)
        sortBy.observeForever(sortTypeObserver)
        sortBy.postValue(SortBy.title)
        direction.postValue(Direction.DESC)
    }

    fun setList() {
        viewModelScope.launch(Dispatchers.IO) {
            list.clear()
            list.addAll(
                databaseProvider.getSortedBy(sortBy.value.toString(), direction.value.toString())
            )
        }
    }

    fun remove(removeAt: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseProvider.deleteAll(
                Note(
                    title = removeAt.title.get().toString(),
                    content = removeAt.content.get().toString(),
                    color = removeAt.color.get().toString(),
                    id = removeAt.id,
                    isEdit = removeAt.isEditable
                )
            )
        }
    }


    fun addNote() {
        navigationEvent.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()
        direction.removeObserver(directionObserver)
        sortBy.removeObserver(sortTypeObserver)
    }
}

