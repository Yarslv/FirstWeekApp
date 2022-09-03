package com.internship.firstweekapp.ui.add_location

import android.location.Geocoder
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentAddLocationBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddLocationFragment :
    BaseFragment<FragmentAddLocationBinding>(R.layout.fragment_add_location) {
    override val viewModel: AddLocationFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        viewModel.sle.observe(viewLifecycleOwner) {
            if (it) {
                try {

                    val bundle = Bundle()

                    val address = viewModel.text

                    val geoCoder = Geocoder(context)
                    val dot = geoCoder.getFromLocationName(address, 1)[0].locality
                    val dot2 = geoCoder.getFromLocationName(dot, 1)[0]

                    bundle.putString(Constants.BUNDLE_KEY,
                        dot2.latitude.toString().substring(0, 5) + "," + dot2.longitude.toString()
                            .substring(0, 5)
                    )

                    setFragmentResult(Constants.LOCATION_REQUEST_KEY, bundle)
                    findNavController().navigateUp()
                } catch (E: Exception) {
                    viewModel.error.set(Error.UnknownLocation)
                }
            }
        }
    }
}