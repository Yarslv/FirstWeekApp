package com.internship.firstweekapp.ui.tabs.setting_fragment

import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentSettingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override val viewModel: SettingFragmentViewModel by viewModel()
}