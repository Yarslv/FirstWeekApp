package com.internship.firstweekapp.ui.setup_screens.confirm_class

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentConfirmClassBinding
import com.internship.firstweekapp.navigate
import com.internship.firstweekapp.navigateBack
import com.internship.firstweekapp.ui.main.MainScreen
import com.internship.firstweekapp.ui.setup_screens.confirm_age.ConfirmAgeFragmentDirections
import com.internship.firstweekapp.ui.setup_screens.confirm_level.ConfirmBtnVariation
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfirmClassFragment :
    BaseFragment<FragmentConfirmClassBinding>(R.layout.fragment_confirm_class) {

    override val viewModel: ConfirmClassFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (requireActivity() as MainScreen).hideBar()
        binding.viewModel = viewModel.apply {
            navigationEvent.observe(viewLifecycleOwner) {
                when (it) {
                    ConfirmBtnVariation.Back -> {
                        navigateBack()
                    }
                    ConfirmBtnVariation.Next -> {
                        navigate(ConfirmAgeFragmentDirections.globalToMapFragment())
                    }
                }
            }
        }
    }

}