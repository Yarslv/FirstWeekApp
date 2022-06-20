package com.internship.firstweekapp.ui.settingFragment

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentSettingBinding


class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override val viewModel: SettingFragmentViewModel by viewModels()

    override fun setObservers() {
        binding.vmodel = viewModel
        viewModel.isStartGameEvent.observe(
            this
        ) {
            if (it.isStart) requireActivity().findNavController(R.id.homeHostNavFragment).navigate(
                SettingFragmentDirections.actionSettingFragmentToBlankFragment(
                    it.isCross,
                    it.isFirst
                )
            )
        }
    }

}