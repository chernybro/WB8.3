package com.chernybro.wb53.presentation

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import com.chernybro.wb53.R
import com.chernybro.wb53.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        findNavController(R.id.nav_host_fragment_content_main).currentDestination
        binding.info.setOnClickListener { findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_global_AboutFragment) }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun toggleInfoButton(needToHide: Boolean) {
        binding.info.visibility = if (needToHide)  View.GONE else View.VISIBLE
    }

    fun navigateToInfo(currentDestination: NavDestination) {
        if (currentDestination.id == R.id.SecondFragment) {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_SecondFragment_to_AboutFragment)
        } else if (currentDestination.id == R.id.FirstFragment) {
            findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_AboutFragment)
        }
    }
}