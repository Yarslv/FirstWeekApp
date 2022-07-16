package com.internship.firstweekapp.ui.add_tag

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseDialogFragment
import com.internship.firstweekapp.arch.ext.hideKeyboard
import com.internship.firstweekapp.databinding.AddTagDialogBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddTagDialogFragment : BaseDialogFragment<AddTagDialogBinding>(R.layout.add_tag_dialog) {
    override val viewModel: AddTagDialogViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        dialog!!.window!!.setBackgroundDrawable(requireContext().getDrawable(R.drawable.search_view_round_white))
        viewModel.singleLiveEvent.observe(viewLifecycleOwner) {
            when (it) {
                ButtonClick.Cancel -> {
                    setResult("")
                }
                ButtonClick.Save -> {
                    setResult(viewModel.text.get().toString())
                }
            }

        }


    }

    private fun setResult(tagText: String) {
        setFragmentResult(Constants.TAG_REQ_KEY, Bundle().apply { putString("tagText", tagText) })
        hideKeyboard()
        dismiss()
    }
}

