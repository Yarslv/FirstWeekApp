package com.internship.firstweekapp.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseActivity
import com.internship.firstweekapp.databinding.ActivityMainBinding
import com.internship.firstweekapp.ui.setup_screens.confirm_age.ConfirmAgeFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreen : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.navBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.map -> {
                    findNavController(binding.homeHostNavFragment.id).navigate(
                        ConfirmAgeFragmentDirections.globalToMapFragment()
                    )
                }
                R.id.points -> {
                    findNavController(binding.homeHostNavFragment.id).navigate(
                        ConfirmAgeFragmentDirections.globalToPointsFragment()
                    )
                }
                else -> {
                    findNavController(binding.homeHostNavFragment.id).navigate(
                        ConfirmAgeFragmentDirections.globalToSettingFragment()
                    )
                }
            }
            true
        }
    }

    fun hideBar() {
        viewModel.barVisibility.set(false)
    }

    fun showBar() {
        viewModel.barVisibility.set(true)
    }

    override val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeHostNavFragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun setObservers() {}


}