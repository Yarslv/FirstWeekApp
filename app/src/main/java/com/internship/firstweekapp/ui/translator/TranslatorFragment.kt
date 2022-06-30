package com.internship.firstweekapp.ui.translator

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentTranslatorBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslatorFragment : BaseFragment<FragmentTranslatorBinding>(R.layout.fragment_translator) {

    override val viewModel: TranslatorFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vmodel = viewModel
        viewModel.navEvent.observe(viewLifecycleOwner) {
            if (it) {
                val dir =
                    TranslatorFragmentDirections.actionTranslatorFragmentToTranslatedWordFragment(
                        viewModel.dictionary.get(viewModel.model.text).toTypedArray()
                    )
                findNavController().navigate(dir)
            }
        }
    }
}