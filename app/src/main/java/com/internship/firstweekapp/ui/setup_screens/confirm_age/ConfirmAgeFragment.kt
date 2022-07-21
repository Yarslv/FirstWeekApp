package com.internship.firstweekapp.ui.setup_screens.confirm_age

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.databinding.FragmentConfirmAgeBinding
import com.internship.firstweekapp.subarch.BaseFragmentWithStorage
import org.koin.androidx.viewmodel.ext.android.viewModel

class
ConfirmAgeFragment :
    BaseFragmentWithStorage<FragmentConfirmAgeBinding>(R.layout.fragment_confirm_age) {
    override val viewModel: ConfirmAgeFragmentViewModel by viewModel()
    override val nextNav = ConfirmAgeFragmentDirections.actionGlobalToLevelFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (storage.getString(Constants.AGE_TAG).toString() == "yes"
        ) {
            goNext()
        }

        binding.viewModel = viewModel.apply {
            navigateEvent.observe(viewLifecycleOwner) {
                if (it) {
                    storage.save(Constants.AGE_TAG, "yes")
                    goNext()
                }
            }
        }
    }


}