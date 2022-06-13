package com.internship.firstweekapp.ui.dummy

import androidx.fragment.app.viewModels
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.databinding.FragmentBlankBinding

class BlankFragment : BaseFragment<FragmentBlankBinding>(R.layout.fragment_blank) {
    override val viewModel: BaseViewModel by viewModels()

}