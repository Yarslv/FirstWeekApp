package com.internship.firstweekapp.ui.tabs_screen

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentTabsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TabsScreenFragment : BaseFragment<FragmentTabsBinding>(R.layout.fragment_tabs) {
    override val viewModel: TabsScreenFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                when (viewModel.currentTab.get()) {
                    TabsNames.List -> {
                        if (!binding.cityList.findNavController().navigateUp()) requireActivity().finishAffinity()
                    }
                    TabsNames.Home -> {
                        if (!binding.homeCity.findNavController().navigateUp()) requireActivity().finishAffinity()
                    }
                    TabsNames.Setting -> {
                        if (!binding.setting.findNavController().navigateUp()) requireActivity().finishAffinity()
                    }
                    null -> {}
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
    }


}