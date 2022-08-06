package com.internship.firstweekapp.ui.card_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentListBinding
import com.internship.firstweekapp.navigate
import com.internship.firstweekapp.util.mappers.SensorEntityToCardItemMapper
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : BaseFragment<FragmentListBinding>(R.layout.fragment_list),
    SwipeRefreshLayout.OnRefreshListener {
    override val viewModel: ListFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.recycler.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.navigationEvent.observe(viewLifecycleOwner) {
            if (it) navigate(ListFragmentDirections.actionListFragmentToAddCardFragment())
        }

        setFragmentResultListener(Constants.FRAGMENT_RESULT_KEY) { _, bundle ->

            viewModel.model.localItems.add(SensorEntityToCardItemMapper().apply {
                id = Constants.SHIFT_FOR_LOCAL_ITEMS_IDS + viewModel.model.localItems.size
            }.toDomain(Json.decodeFromString(bundle.getString(Constants.FRAGMENT_RESULT_VARIABLE_KEY).toString())))
        }

        binding.refreshLayout.setOnRefreshListener(this)

    }

    override fun onRefresh() {
        viewModel.getSensorsFromRepository()
        viewModel.updateLocal()
        binding.refreshLayout.isRefreshing = false
    }
}

