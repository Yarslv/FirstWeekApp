package com.internship.firstweekapp.ui.setup_screens.confirm_level

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.databinding.FragmentConfirmLevelBinding
import com.internship.firstweekapp.subarch.BaseFragmentWithStorage
import com.internship.firstweekapp.ui.setup_screens.confirm_age.ConfirmAgeFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel


class ConfirmLevelFragment :
    BaseFragmentWithStorage<FragmentConfirmLevelBinding>(R.layout.fragment_confirm_level) {
    override val viewModel: ConfirmLevelFragmentViewModel by viewModel()
    override val nextNav = ConfirmAgeFragmentDirections.actionGlobalToClassFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (storage.getInt(Constants.LEVEL_TAG) != -1) {
            goNext()
        }

        binding.viewModel = viewModel.apply {
            navigationEvent.observe(viewLifecycleOwner) {
                when (it) {
                    ConfirmBtnVariation.Back -> {
                        goBack()
                    }
                    ConfirmBtnVariation.Next -> {
                        storage.save(Constants.LEVEL_TAG, viewModel.sliderValue.get()!!.toInt())
                        goNext()
                    }
                }

            }
        }
    }
}