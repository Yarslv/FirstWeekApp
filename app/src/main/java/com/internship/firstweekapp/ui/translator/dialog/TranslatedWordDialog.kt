package com.internship.firstweekapp.ui.translator.dialog

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseDialogFragment
import com.internship.firstweekapp.databinding.FragmentTranslatedWordDialogBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TranslatedWordDialog :
    BaseDialogFragment<FragmentTranslatedWordDialogBinding>(R.layout.fragment_translated_word_dialog) {
    private val navArgs: TranslatedWordDialogArgs by navArgs()
    override val viewModel: TranslatedWordDialogFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vmodel = viewModel
        viewModel.words = navArgs.words
    }
}