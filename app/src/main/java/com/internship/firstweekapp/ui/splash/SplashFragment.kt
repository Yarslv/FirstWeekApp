package com.internship.firstweekapp.ui.splash

import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.SplashFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<SplashFragmentBinding>(R.layout.splash_fragment) {

    override val viewModel: SplashViewModel by viewModel()

    override fun setObservers() {
        viewModel.initEvent.observe(this) {
            if (it) findNavController().navigate(
                SplashFragmentDirections.actionSplashFragmentToGameFragment(
                    "start"
                )
            )
        }
    }
}