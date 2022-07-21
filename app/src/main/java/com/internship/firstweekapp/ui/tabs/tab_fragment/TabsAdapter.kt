package com.internship.firstweekapp.ui.tabs.tab_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsAdapter(
    private var list: List<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int =
        list.size

    override fun createFragment(position: Int): Fragment = list[position]
}