package com.internship.firstweekapp.ui.blank

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentBlankBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BlankFragment(var text: String) :
    BaseFragment<FragmentBlankBinding>(R.layout.fragment_blank) {
    override val viewModel: BlankFragmentViewModel by viewModel {
        parametersOf(text)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.model = viewModel
    }
}