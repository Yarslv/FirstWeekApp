package com.internship.firstweekapp.ui.tabs

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.TabsFragmentBinding
import com.internship.firstweekapp.ui.add_task.AddTaskFragmentDirections
import com.internship.firstweekapp.ui.blank.BlankFragment
import com.internship.firstweekapp.ui.task.TaskFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class TabsFragment : BaseFragment<TabsFragmentBinding>(R.layout.tabs_fragment) {

    override val viewModel: TabsFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vmodel = viewModel

        binding.viewPager.adapter = ViewPagerAdapter(
            arrayListOf(
                BlankFragment("Home Not Available Now"),
                TaskFragment(), BlankFragment("Activity Not Available Now"),
                BlankFragment("Folder Not Available Now")
            ), childFragmentManager, lifecycle
        )
        viewModel.singleLiveEvent.observe(viewLifecycleOwner) {
            when (it) {
                Directions.AddTask -> findNavController().navigate(TabsFragmentDirections.actionTabsFragmentToAddTaskFragment())
                Directions.ViewCalendar -> findNavController().navigate(AddTaskFragmentDirections.showCalendar())
            }
        }
    }
}