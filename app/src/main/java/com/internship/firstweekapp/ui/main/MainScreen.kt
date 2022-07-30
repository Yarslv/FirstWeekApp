package com.internship.firstweekapp.ui.main

import android.content.Intent
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseActivity
import com.internship.firstweekapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreen : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    override val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeHostNavFragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    fun showDrawer(){
        binding.drawerLayout.open()
    }
    fun hideDrawer(){
        binding.drawerLayout.closeDrawer(GravityCompat.START)
    }

    fun getNavigationView(): NavigationView {
        return binding.navigationView
    }

    override fun onBackPressed(){
        
    }

    override fun setObservers() {}


}