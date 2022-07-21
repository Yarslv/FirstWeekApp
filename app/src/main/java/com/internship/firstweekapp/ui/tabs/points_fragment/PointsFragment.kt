package com.internship.firstweekapp.ui.tabs.points_fragment

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentPointsBinding
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PointsFragment : BaseFragment<FragmentPointsBinding>(R.layout.fragment_points) {
    override val viewModel: PointsFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.recycler.adapter = PointsRecyclerViewAdapter()

        viewModel.sli.observe(viewLifecycleOwner) {
            if (it) addFromClipboard()
        }
    }

    private fun addFromClipboard() {
        val service =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        try {
            JSONObject(
                // for ctrl+c
                //{"level": 99,"class": "cSass","latitude": 34.15,"longtitude": 2.17}
                //{"level": 6,"class": "cdass","latitude": 55.15,"longtitude": 26.17}
                //{"level": 15,"class": "class","latitude": 9.15,"longtitude": 36.17}
                service.primaryClip?.getItemAt(0)?.text.toString()
            ).apply {
                viewModel.addNewPoint(
                    PointModel(
                        this.getString(Constants.LEVEL_TAG).toString(),
                        this.getString(Constants.CLASS_TAG).toString(),
                        this.getString(Constants.LATITUDE_TAG).toDouble(),
                        this.getString(Constants.LONGTITUDE_TAG).toDouble()
                    )
                )
            }
        } catch (
            e: org.json.JSONException
        ) {
            showToast(getString(R.string.unsupported_text_msg))
        }


    }
}