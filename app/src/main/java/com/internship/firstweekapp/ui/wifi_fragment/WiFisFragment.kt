package com.internship.firstweekapp.ui.wifi_fragment

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentWifisBinding
import com.internship.firstweekapp.databinding.WifisCircleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WiFisFragment : BaseFragment<FragmentWifisBinding>(R.layout.fragment_wifis) {
    override val viewModel: WiFisFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vmodel = viewModel

        checkPermission()

        viewModel.manager.receiver.receive().observe(viewLifecycleOwner) {

            val arr = arrayListOf<View>()
            val angleOne = CIRCLE_DEGREE_TOTAL / it.size

            it.forEachIndexed { index, element ->
                arr.add(
                    with(WifisCircleBinding.inflate(LayoutInflater.from(requireContext()))) {
                        this.vmodel = CircleModel(element).apply {
                            singleLiveEvent.observe(viewLifecycleOwner) { dir ->
                                findNavController().navigate(dir)
                            }
                        }
                        this.root.apply {
                            id = View.generateViewId()
                            ConstraintLayout.LayoutParams(
                                DIAMETER, DIAMETER
                            ).apply {
                                circleAngle = angleOne * index
                                circleConstraint = binding.centerView.id
                                circleRadius = element.level * 2 + 200 + DIAMETER
                            }.let { cnstrParam ->
                                alpha = 0f
                                layoutParams = cnstrParam
                                animate().alpha(1f).duration = ANIM_DURATION_MS
                            }
                        }
                    }
                )
            }



            viewModel.list.postValue(arr)
        }

        viewModel.getList().observe(viewLifecycleOwner) {
            for (i in it) {
                binding.constraint.addView(i)
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
        const val DIAMETER = 100
        const val ANIM_DURATION_MS = 1500L
    }
}