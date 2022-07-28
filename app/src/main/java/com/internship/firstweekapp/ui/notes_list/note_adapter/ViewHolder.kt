package com.internship.firstweekapp.ui.notes_list.note_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.databinding.NoteItemBinding
import com.internship.firstweekapp.ui.notes_list.note_item.NoteViewModel

class ViewHolder private constructor(private val binding: NoteItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(model: NoteViewModel) {
        binding.viewModel = model

    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                NoteItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)
        }
    }
}