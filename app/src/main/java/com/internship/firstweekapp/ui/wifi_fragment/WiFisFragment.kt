package com.internship.firstweekapp.ui.wifi_fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentWifisBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Integer.min

class WiFisFragment : BaseFragment<FragmentWifisBinding>(R.layout.fragment_wifis) {
    override val viewModel: WiFisFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vmodel = viewModel

        checkPermission()

        viewModel.manager.receiver.receive().observe(viewLifecycleOwner) {
            viewModel.list.postValue(it)
        }

        viewModel.getList().observe(viewLifecycleOwner) { it ->

            val angleOne = CIRCLE_DEGREE_TOTAL / it.size
            val radiusOne = min(binding.constraint.width, binding.constraint.height) / 30 / 2

            it.forEachIndexed { index, element ->
                binding.constraint.addView(
                    AppCompatTextView(requireContext()).apply {
                        text = element.name
                        id = View.generateViewId()
                        ConstraintLayout.LayoutParams(
                            ConstraintLayout.LayoutParams.WRAP_CONTENT,
                            ConstraintLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            circleAngle = angleOne * index
                            circleConstraint = binding.centerView.id
                            circleRadius =
                                if (element.freq == -FREQUENCY_RANGE_MAX) radiusOne * FREQUENCY_RANGE_MAX else element.freq * radiusOne
                        }.let { layoutParams = it }
                    }
                )
            }
        }
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
                1
            )
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().registerReceiver(
            viewModel.manager.receiver,
            viewModel.manager.getIntentFilter()
        )
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(viewModel.manager.receiver)
    }

    companion object {
        const val CIRCLE_DEGREE_TOTAL = 360f
        const val FREQUENCY_RANGE_MAX = 30
    }
}