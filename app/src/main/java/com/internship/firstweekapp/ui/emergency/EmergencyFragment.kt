package com.internship.firstweekapp.ui.emergency

import android.os.Bundle

import android.view.View
import androidx.fragment.app.setFragmentResultListener
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.databinding.FragmenEmergencyBinding
import com.internship.firstweekapp.ui.main.MainScreen
import com.internship.firstweekapp.ui.notes_list.NotesListFragmentDirections
import com.internship.firstweekapp.util.navigate
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmergencyFragment:BaseFragment<FragmenEmergencyBinding>(R.layout.fragmen_emergency) {
    override val viewModel: EmergencyFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.viewModel = viewModel
        setFragmentResultListener(Constants.REQUEST_KEY,) { s: String, bundle: Bundle ->
            if (bundle.getBoolean(Constants.VARIABLE_TAG)) {
                viewModel.delete()
            }
        }
        viewModel.navigationEvent.observe(viewLifecycleOwner) {
            if (it) {
                navigate(EmergencyFragmentDirections.actionEmergencyFragmentToEmergencyDialogFragment())
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            (requireActivity() as MainScreen).showDrawer()
        }
    }
}