package com.internship.firstweekapp.ui.choose_language

import android.content.Context
import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentChooseLanguageBinding
import com.internship.firstweekapp.ui.tabs.setting.Languages
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ChooseLanguageFragment :
    BaseFragment<FragmentChooseLanguageBinding>(R.layout.fragment_choose_language) {
    override val viewModel: ChooseLanguageFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        viewModel.changeLanguageEvent.observe(viewLifecycleOwner) {
            onAttach(requireContext().changeLocale(it))
            requireActivity().recreate()
        }
    }

    private fun Context.changeLocale(newLanguage: Languages): Context {
        val locale = when (newLanguage) {
            Languages.UK -> Locale("uk")
            Languages.EN -> Locale.ENGLISH
        }

        Locale.setDefault(locale)

        val config = this.resources.configuration
        config.setLocale(locale)
        onConfigurationChanged(config)
        return createConfigurationContext(config)
    }
}