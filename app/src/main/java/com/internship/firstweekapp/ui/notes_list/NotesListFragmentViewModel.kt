package com.internship.firstweekapp.ui.notes_list

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.room.DatabaseProvider
import com.internship.firstweekapp.room.Note
import com.internship.firstweekapp.ui.notes_list.note_item.NoteViewModel
import com.internship.firstweekapp.util.Direction
import com.internship.firstweekapp.util.SortBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesListFragmentViewModel(val databaseProvider: DatabaseProvider) : BaseViewModel() {

    val list = ObservableArrayList<NoteViewModel>()
    val sortBy = MutableLiveData<SortBy>()
    private val sortTypeObserver = Observer<SortBy> {
        viewModelScope.launch(Dispatchers.IO) {}
    }

    val direction = MutableLiveData<Direction>()

    val navigationEvent = SingleLiveEvent<Boolean>()

    private val directionObserver = Observer<Direction> {
        viewModelScope.launch(Dispatchers.IO) {
            list.clear()
            setList(it)
        }
    }

    init {
        direction.observeForever(directionObserver)
        sortBy.observeForever(sortTypeObserver)
        sortBy.postValue(SortBy.Title)
        direction.postValue(Direction.Reverse)
    }

    fun setList(it: Direction) {
        viewModelScope.launch(Dispatchers.IO) {
            list.clear()
            when (it) {
                Direction.Normal -> {
                    when (sortBy.value) {
                        SortBy.Date -> list.addAll(
                            databaseProvider.getAll().sortedBy { it.title.get() })
                        SortBy.Title -> list.addAll(
                            databaseProvider.getAll().sortedBy { it.title.get() })
                        SortBy.Color -> list.addAll(
                            databaseProvider.getAll().sortedBy { it.color.get() })
                        SortBy.Content -> list.addAll(
                            databaseProvider.getAll().sortedBy { it.content.get() })
                    }
                }

                Direction.Reverse ->
                    when (sortBy.value) {
                        SortBy.Date -> list.addAll(
                            databaseProvider.getAll().sortedByDescending { it.title.get() })
                        SortBy.Title -> list.addAll(
                            databaseProvider.getAll().sortedByDescending { it.title.get() })
                        SortBy.Color -> list.addAll(
                            databaseProvider.getAll().sortedByDescending { it.color.get() })
                        SortBy.Content -> list.addAll(
                            databaseProvider.getAll().sortedByDescending { it.content.get() })
                    }
            }
        }
    }

    fun remove(removeAt: NoteViewModel) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseProvider.delete(
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

