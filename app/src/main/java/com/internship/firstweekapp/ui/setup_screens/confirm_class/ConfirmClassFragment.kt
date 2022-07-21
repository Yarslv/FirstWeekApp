package com.internship.firstweekapp.ui.setup_screens.confirm_class

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.databinding.FragmentConfirmClassBinding
import com.internship.firstweekapp.subarch.BaseFragmentWithStorage
import com.internship.firstweekapp.ui.setup_screens.confirm_age.ConfirmAgeFragmentDirections
import com.internship.firstweekapp.ui.setup_screens.confirm_level.ConfirmBtnVariation
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfirmClassFragment :
    BaseFragmentWithStorage<FragmentConfirmClassBinding>(R.layout.fragment_confirm_class) {

    override val viewModel: ConfirmClassFragmentViewModel by viewModel()

    override val nextNav = ConfirmAgeFragmentDirections.actionGlobalToTabsFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (storage.getString(Constants.CLASS_TAG, "").toString().isNotEmpty()) {
            goNext()
        }


        binding.viewModel = viewModel.apply {
            navigationEvent.observe(viewLifecycleOwner) {
                when (it) {
                    ConfirmBtnVariation.Back -> {
                        goBack()
                    }
                    ConfirmBtnVariation.Next -> {
                        when (viewModel.selectedClass) {
                            UserClass.None -> {}
                            UserClass.Player -> {
                                saveAndNavigate(Constants.PLAYER_TAG)
                            }
                            UserClass.Hero -> {
                                saveAndNavigate(Constants.HERO_TAG)
                            }
                            UserClass.Master -> {
                                saveAndNavigate(Constants.MASTER_TAG)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun saveAndNavigate(value: String) {
        storage.save(Constants.CLASS_TAG, value)
        goNext()
    }


}