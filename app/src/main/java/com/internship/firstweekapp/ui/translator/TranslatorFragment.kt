package com.internship.firstweekapp.ui.translator

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentTranslatorBinding
import com.internship.firstweekapp.dict.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslatorFragment : BaseFragment<FragmentTranslatorBinding>(R.layout.fragment_translator) {

    override val viewModel: TranslatorFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vmodel = viewModel

        viewModel.navEvent.observe(viewLifecycleOwner) { it ->
            if (it) {
                val arr = arrayListOf<String>()
                viewModel.dictionary.get(viewModel.model.text).forEach {
                    if (viewModel.dictionary.state == State.STANDARD) {
                        arr.add(it.wordTrans)
                    } else {
                        arr.add(it.wordOrig)
                    }
                }
                val dir =
                    TranslatorFragmentDirections.actionTranslatorFragmentToTranslatedWordFragment(
                        arr.toTypedArray()

                    )
                findNavController().navigate(dir)
            }
        }
    }
}