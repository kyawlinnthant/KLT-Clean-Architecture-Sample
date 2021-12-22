package com.example.klt_clean_architecture_sample.presentation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.klt_clean_architecture_sample.R
import com.example.klt_clean_architecture_sample.databinding.ActivityMainBinding
import com.example.klt_clean_architecture_sample.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
    }
    private val navController: NavController by lazy {
        navHostFragment.navController
    }
    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }

    override fun init() {
        setupNavigation()
        Log.d("lelele","here")
    }

    override fun setupListener() {

    }

    override fun observe() {

    }

    private fun setupNavigation() {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


}