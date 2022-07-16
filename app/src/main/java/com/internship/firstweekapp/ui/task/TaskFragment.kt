package com.internship.firstweekapp.ui.task

import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentTaskBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskFragment : BaseFragment<FragmentTaskBinding>(R.layout.fragment_task) {
    override val viewModel: TaskFragmentViewModel by viewModel()

}