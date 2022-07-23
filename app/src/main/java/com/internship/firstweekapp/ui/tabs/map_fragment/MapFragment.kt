package com.internship.firstweekapp.ui.tabs.map_fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.LocationRequest
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentMapBinding
import com.internship.firstweekapp.subarch.ScreenModel
import com.internship.firstweekapp.ui.main.MainScreen
import com.internship.firstweekapp.ui.setup_screens.confirm_class.ConfirmClassFragmentViewModel
import com.internship.firstweekapp.ui.setup_screens.confirm_class.UserClass
import com.internship.firstweekapp.ui.setup_screens.confirm_level.ConfirmLevelFragmentViewModel
import com.internship.firstweekapp.ui.tabs.points_fragment.PointsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback {
    override val viewModel: MapFragmentViewModel by viewModel()
    private val pointsViewModel: PointsFragmentViewModel by viewModel()

    private var myLatitude = 0.0
    private var myLongtitude = 0.0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainScreen).showBar()
        binding.mapView.onCreate(Bundle())
        binding.mapView.getMapAsync(this)

        LocationServices.getFusedLocationProviderClient(requireContext()).getCurrentLocation(
            LocationRequest.QUALITY_BALANCED_POWER_ACCURACY, object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                    return CancellationTokenSource().token
                }

                override fun isCancellationRequested(): Boolean {
                    return false
                }

            }
        ).addOnSuccessListener {
            myLatitude = it.latitude
            myLongtitude = it.longitude
        }
    }


    override fun onMapReady(p0: GoogleMap) {

        val level = ScreenModel<Float>(requireContext()).apply {
            setName(ConfirmLevelFragmentViewModel::class.java)
            value.set(0f)
            readModel()
        }.value.get()

        val clas = ScreenModel<UserClass>(requireContext()).apply {
            setName(ConfirmClassFragmentViewModel::class.java)
            value.set(UserClass.None)
            readModel()
        }.value.get()
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            requireActivity().requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
        } else {


            p0.addMarker(getMarkerOptions(myLatitude, myLongtitude, "I", clas!!))

            pointsViewModel.list.observe(viewLifecycleOwner) { list ->
                list.forEach {
                    p0.addMarker(
                        getMarkerOptions(
                            it.latitude,
                            it.longtitude,
                            it.classType.toString() + it.level,
                            it.classType
                        )
                    )
                }
            }

            binding.exportBtn.setOnClickListener {
                val share = Intent.createChooser(Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        requireContext().getString(
                            R.string.json_format,
                            level,
                            clas,
                            myLatitude, Calendar.getInstance().timeInMillis, myLongtitude
                        )
                    )
                    type = EXPORT_TYPE

                }, null)
                startActivity(share)
            }

            p0.uiSettings.isMyLocationButtonEnabled = true
            p0.isMyLocationEnabled = true
        }
    }

    private fun getMarkerOptions(
        latitude: Double,
        longtitude: Double,
        title: String,
        userClass: UserClass
    ): MarkerOptions {
        return MarkerOptions().apply {
            position(LatLng(latitude, longtitude))
            title(title)

            icon(
                BitmapDescriptorFactory.fromBitmap(
                    Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(
                            resources,
                            when (userClass) {
                                UserClass.Player -> R.raw.player
                                UserClass.None -> {
                                    R.color.black
                                }
                                UserClass.Hero -> R.raw.hero
                                UserClass.Master -> R.raw.master
                            }

                        ), 125, 125, false
                    )
                )
            )
        }
    }

    companion object {
        const val EXPORT_TYPE = "text/plain"
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

}