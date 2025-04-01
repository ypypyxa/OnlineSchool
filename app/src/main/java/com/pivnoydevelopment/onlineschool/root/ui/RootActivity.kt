package com.pivnoydevelopment.onlineschool.root.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pivnoydevelopment.onlineschool.R
import com.pivnoydevelopment.onlineschool.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.onboardingFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    binding.fender.visibility = View.GONE
                }
                R.id.authFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    binding.fender.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    binding.fender.visibility = View.VISIBLE
                }
            }
        }
    }
}