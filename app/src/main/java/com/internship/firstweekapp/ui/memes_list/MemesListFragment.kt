package com.internship.firstweekapp.ui.memes_list

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentMemesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MemesListFragment : BaseFragment<FragmentMemesListBinding>(R.layout.fragment_memes_list) {
    override val viewModel: MemesListFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }

    }
}