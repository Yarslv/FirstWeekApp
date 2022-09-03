package com.internship.firstweekapp.ui.tabs.cities_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentCitiesListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CitiesListFragment : BaseFragment<FragmentCitiesListBinding>(R.layout.fragment_cities_list) {
    override val viewModel: CitiesListFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        setFragmentResultListener(Constants.LOCATION_REQUEST_KEY) { _, bundle ->
            viewModel.addLocation(bundle.getString(Constants.BUNDLE_KEY).toString())
        }

        viewModel.sle.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(CitiesListFragmentDirections.actionCitiesListFragmentToAddLocationFragment())
            }
        }

        val swipeHandler = object : ItemTouchHelper.SimpleCallback(1, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) {
                    viewModel.items.removeAt(viewHolder.adapterPosition)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.recycler)

    }
}