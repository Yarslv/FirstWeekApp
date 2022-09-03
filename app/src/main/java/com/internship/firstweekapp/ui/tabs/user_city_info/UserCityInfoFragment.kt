package com.internship.firstweekapp.ui.tabs.user_city_info

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentUserCityInfoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserCityInfoFragment :
    BaseFragment<FragmentUserCityInfoBinding>(R.layout.fragment_user_city_info) {
    override val viewModel: UserCityInfoFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

//        findNavController().navigate(TabsScreenFragmentDirections.actionTabsScreenFragmentToForecastFragment())
    }
}