package com.internship.firstweekapp.ui.tabs.tab_fragment

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentTabBinding
import com.internship.firstweekapp.ui.tabs.map_fragment.MapFragment
import com.internship.firstweekapp.ui.tabs.points_fragment.PointsFragment
import com.internship.firstweekapp.ui.tabs.setting_fragment.SettingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TabsFragment : BaseFragment<FragmentTabBinding>(R.layout.fragment_tab) {
    override val viewModel: TabsFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = TabsAdapter(
            arrayListOf(MapFragment(), PointsFragment(), SettingFragment()),
            childFragmentManager,
            lifecycle
        )
        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.map -> {
                    binding.viewPager.setCurrentItem(0, false)
                }
                R.id.points -> {
                    binding.viewPager.setCurrentItem(1, false)
                }
                else -> {
                    binding.viewPager.setCurrentItem(2, false)
                }
            }
            true
        }

    }
}