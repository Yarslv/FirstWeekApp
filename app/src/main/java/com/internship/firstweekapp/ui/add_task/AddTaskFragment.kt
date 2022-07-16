package com.internship.firstweekapp.ui.add_task

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.AddTaskFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddTaskFragment : BaseFragment<AddTaskFragmentBinding>(R.layout.add_task_fragment) {
    override val viewModel: AddTaskViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vmodel = viewModel

        viewModel.sli.observe(viewLifecycleOwner) {
            when (it) {
                Directions.Date -> {
                    findNavController().navigate(AddTaskFragmentDirections.showCalendar())
                    setFragmentResultListener(Constants.CALENDAR_REQ_KEY) { _, value ->

                        viewModel.date.set(
                            requireContext().getString(
                                R.string.concate_data,
                                value.getString("day"),
                                value.getString("month"),
                                value.getString(
                                    "year"
                                )
                            )
                        )
                    }

                }

                Directions.TimeFrom -> {
                    findNavController().navigate(AddTaskFragmentDirections.showTime())
                    setFragmentResultListener(Constants.TIME_REQ_KEY) { _, value ->
                        viewModel.timeFromHours.set(
                            value.getInt("hours")
                        )
                        viewModel.timeFromMinutes.set(
                            value.getInt("minutes")
                        )
                    }
                }
                Directions.TimeTo -> {
                    findNavController().navigate(AddTaskFragmentDirections.showTime())
                    setFragmentResultListener(Constants.TIME_REQ_KEY) { key, value ->
                        viewModel.timeToHours.set(
                            value.getInt("hours")
                        )

                        viewModel.timeToMinutes.set(
                            value.getInt("minutes")
                        )
                    }
                }
                Directions.Back -> findNavController().popBackStack()
                Directions.AddNewTag -> {
                    findNavController().navigate(AddTaskFragmentDirections.actionAddTaskFragmentToAddTagDialogFragment())
                    setFragmentResultListener(Constants.TAG_REQ_KEY) { _, value ->
                        with(value.getString("tagText").toString()) {
                            if (this.isNotEmpty())
                                (binding.tagsRecycler.adapter as TagsAdapter).addContent(
                                    TagModel(
                                        this, Color.BLUE
                                    )
                                )
                        }
                    }

                }
            }


        }
        binding.tagsRecycler.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexDirection = FlexDirection.ROW

        }
        binding.tagsRecycler.adapter =
            TagsAdapter().apply { setContent(arrayListOf(TagModel("ss", Color.GREEN))) }
    }
}