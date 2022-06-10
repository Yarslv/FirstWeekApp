package com.internship.firstweekapp.ui.fragments.startFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.R
import com.internship.firstweekapp.util.Constants

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val action = StartFragmentDirections
            .actionStartFragmentToFirstScreenFragment(Constants.START_SCREEN, arrayOf())
        findNavController().navigate(action)
        super.onViewCreated(view, savedInstanceState)
    }
}