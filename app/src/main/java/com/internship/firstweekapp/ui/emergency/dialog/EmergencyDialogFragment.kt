package com.internship.firstweekapp.ui.emergency.dialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseDialogFragment
import com.internship.firstweekapp.databinding.DialogFragmentClearBinding
import com.internship.firstweekapp.util.navigateBack
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmergencyDialogFragment: BaseDialogFragment<DialogFragmentClearBinding>(R.layout.dialog_fragment_clear) {
    override val viewModel: EmergencyDialogFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        viewModel.navigationEvent.observe(viewLifecycleOwner){
            if (it){
                setFragmentResult(Constants.REQUEST_KEY, Bundle().apply{putBoolean(Constants.VARIABLE_TAG, true)})
                Toast.makeText(requireContext(), getString(R.string.cleared_toast_text), Toast.LENGTH_SHORT).show()
                navigateBack()
            }else{
                setFragmentResult(Constants.REQUEST_KEY, Bundle().apply{putBoolean(Constants.VARIABLE_TAG, false)})
                navigateBack()
            }

        }
    }
}