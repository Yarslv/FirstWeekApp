package com.internship.firstweekapp.ui.forecast

import android.os.Bundle
import android.view.View
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment

import com.internship.firstweekapp.databinding.WheatherForecastBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastFragment : BaseFragment<WheatherForecastBinding>(R.layout.wheather_forecast) {
    override val viewModel: ForestFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
    }
}