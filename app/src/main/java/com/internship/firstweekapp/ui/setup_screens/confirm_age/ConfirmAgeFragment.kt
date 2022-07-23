package com.internship.firstweekapp.ui.setup_screens.confirm_age

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentConfirmAgeBinding
import com.internship.firstweekapp.navigate
import com.internship.firstweekapp.ui.main.MainScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class
ConfirmAgeFragment :
    BaseFragment<FragmentConfirmAgeBinding>(R.layout.fragment_confirm_age) {
    override val viewModel: ConfirmAgeFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainScreen).hideBar()

        binding.viewModel = viewModel.apply {
            navigateEvent.observe(viewLifecycleOwner) {
                if (it) {
                    navigate(ConfirmAgeFragmentDirections.actionGlobalToLevelFragment())
                }
            }
        }
    }
}