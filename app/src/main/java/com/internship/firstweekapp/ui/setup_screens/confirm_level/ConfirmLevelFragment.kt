package com.internship.firstweekapp.ui.setup_screens.confirm_level

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentConfirmLevelBinding
import com.internship.firstweekapp.navigate
import com.internship.firstweekapp.navigateBack
import com.internship.firstweekapp.ui.setup_screens.confirm_age.ConfirmAgeFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel


class ConfirmLevelFragment :
    BaseFragment<FragmentConfirmLevelBinding>(R.layout.fragment_confirm_level) {
    override val viewModel: ConfirmLevelFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.viewModel = viewModel.apply {
            navigationEvent.observe(viewLifecycleOwner) {
                when (it) {
                    ConfirmBtnVariation.Back -> {
                        navigateBack()
                    }
                    ConfirmBtnVariation.Next -> {
                        navigate(ConfirmAgeFragmentDirections.actionGlobalToClassFragment())
                    }
                }

            }
        }
    }
}