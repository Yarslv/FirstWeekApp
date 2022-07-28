package com.internship.firstweekapp.ui.add_note

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentAddEditNotesBinding
import com.internship.firstweekapp.util.navigateBack
import com.internship.firstweekapp.util.AddOrEditFlag
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddEditNoteFragment :
    BaseFragment<FragmentAddEditNotesBinding>(R.layout.fragment_add_edit_notes) {
    private val navArgs: AddEditNoteFragmentArgs by navArgs()

    override val viewModel: AddEditNoteFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel.apply {
            isAddOrEdit.set(AddOrEditFlag.valueOf(navArgs.type))
            id = navArgs.itemNumber
            getData()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.saveItem -> {
                    if (viewModel.save())
                        navigateBack()
                }
            }
            true
        }
        binding.toolbar.setNavigationOnClickListener {
            navigateBack()
        }

        viewModel.toastEvent.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(), when (it) {
                    Error.TitleIsEmpty -> requireContext().getString(R.string.title_incorrect)
                    Error.ContentIsEmpty -> requireContext().getString(R.string.content_incorrect)
                }, Toast.LENGTH_SHORT
            ).show()
        }

    }

}