package com.internship.firstweekapp.ui.tabs.map_fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentMapBinding
import com.internship.firstweekapp.ui.tabs.points_fragment.PointsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {
    override val viewModel: MapFragmentViewModel by viewModel()
    private val pointsViewModel: PointsFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
            .getMapAsync { p0 ->
                pointsViewModel.list.observe(viewLifecycleOwner) { list ->
                    list.forEach {
                        p0.addMarker(MarkerOptions().apply {
                            position(LatLng(it.latitude, it.longtitude))
                            title(it.classType + it.level)
                        })
                    }
                }
            }

        binding.exportBtn.setOnClickListener {
            val share = Intent.createChooser(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    requireContext().getString(
                        R.string.json_format,
                        DUMMY_LEVEL,
                        DUMMY_CLASS,
                        DUMMY_LATITUDE,
                        DUMMY_LONGTITUDE
                    )
                )
                type = EXPORT_TYPE

            }, null)
            startActivity(share)
        }
    }


    companion object {
        const val EXPORT_TYPE = "text/plain"
        const val DUMMY_LEVEL = 0
        const val DUMMY_CLASS = "userName"
        const val DUMMY_LATITUDE = 14.15
        const val DUMMY_LONGTITUDE = 16.17
    }
}