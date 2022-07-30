package com.internship.firstweekapp.ui.help

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.databinding.FragmentHelpBinding
import com.internship.firstweekapp.ui.main.MainScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpFragment:BaseFragment<FragmentHelpBinding>(R.layout.fragment_help) {
    override val viewModel: HelpFragmentViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolbar.setNavigationOnClickListener {
            (requireActivity() as MainScreen).showDrawer()
        }
    }
}