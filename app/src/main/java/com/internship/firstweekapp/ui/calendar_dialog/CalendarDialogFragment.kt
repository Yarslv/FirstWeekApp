package com.internship.firstweekapp.ui.calendar_dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseDialogFragment
import com.internship.firstweekapp.databinding.DialogFragmentCalendarBinding
import com.internship.firstweekapp.ui.add_tag.ButtonClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarDialogFragment :
    BaseDialogFragment<DialogFragmentCalendarBinding>(R.layout.dialog_fragment_calendar) {
    override val viewModel: CalendarDialogFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vmodel = viewModel
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 7)

        dialog!!.window!!.setBackgroundDrawable(requireContext().getDrawable(R.drawable.search_view_round_white))
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
            Constants.CALENDAR_REQ_KEY, Bundle().apply {
                putString("year", viewModel.year.get().toString())
                putString("month", viewModel.month.get().toString())
                putString("day", (binding.recycler.adapter as Adapter).getSelected())
            }

        )
        dismiss()
    }
}