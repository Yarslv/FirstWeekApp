package com.internship.firstweekapp.ui.notes_list.note_adapter

import android.app.Activity
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.adapter.BaseRecyclerAdapter
import com.internship.firstweekapp.util.AddOrEditFlag
import com.internship.firstweekapp.ui.notes_list.NotesListFragmentDirections
import com.internship.firstweekapp.ui.notes_list.note_item.NoteModel

class NotesRecyclerAdapter : BaseRecyclerAdapter<ViewHolder, NoteModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(adapterItems[position].apply {
            navEvent.observe(holder.itemView.context as LifecycleOwner) {
                if (it) Navigation.findNavController((holder.itemView.context as Activity), R.id.homeHostNavFragment)
                    .navigate(NotesListFragmentDirections.actionNotesListFragmentToAddEditNoteFragment(
                        AddOrEditFlag.Edit.name, this.id))
            }
        })
    }
}

