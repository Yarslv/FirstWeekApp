package com.internship.firstweekapp.ui.search

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentSearchBinding
import com.internship.firstweekapp.navigate
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    override val viewModel: SearchFragmentViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        viewModel.querySingleLiveEvent.observe(viewLifecycleOwner) {
            navigate(SearchFragmentDirections.actionSearchFragmentToResultListFragment(it))
        }
    }
}