package com.internship.firstweekapp.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.internship.firstweekapp.databinding.FragmentDialogBinding

class DialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogBinding
    private val arg by navArgs<DialogFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.textView.text = arg.info.replace(", ", "\n\n")
    }

}