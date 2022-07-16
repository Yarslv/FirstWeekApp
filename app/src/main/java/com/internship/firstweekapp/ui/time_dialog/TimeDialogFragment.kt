package com.internship.firstweekapp.ui.time_dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseDialogFragment
import com.internship.firstweekapp.databinding.DialogTimeFragmentBinding
import com.internship.firstweekapp.ui.add_tag.ButtonClick
import org.koin.androidx.viewmodel.ext.android.viewModel


class TimeDialogFragment :
    BaseDialogFragment<DialogTimeFragmentBinding>(R.layout.dialog_time_fragment) {
    override val viewModel: TimeDialogFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog!!.window!!.setBackgroundDrawable(requireContext().getDrawable(R.drawable.search_view_round_white))
        binding.viewModel = viewModel
        viewModel.singleLiveEvent.observe(viewLifecycleOwner) {
            when (it) {
                ButtonClick.Cancel -> {
                    dismiss()
                }
                ButtonClick.Save -> {
                    setResult()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setResult() {
        setFragmentResult(
            Constants.TIME_REQ_KEY, Bundle().apply {
                putInt("hours", binding.hoursPicker.value)
                putInt("minutes", binding.minutesPicker.value)
            }
        )
        dismiss()
    }
}

