package com.froggie.design

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.froggie.design.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolBarBg()
    }


    private fun initToolBarBg() {
        //  Glide.with(this).load(R.drawable.froggie_tool_bg).into()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        setSupportActionBar(binding.toolbar)
        navigationController = navHostFragment.navController
        setupActionBarWithNavController(navigationController)
        hideAndShowViewComponents();
    }

    private fun hideAndShowViewComponents() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            run {
                when (destination.id) {
                    R.id.homeFragment -> {
                        // binding.tvHeaderTitle.isVisible = true
                        binding.bottomNavigationBar.isVisible = true
                    }

                    else -> {
                        // binding.tvHeaderTitle.isVisible = false
                        binding.bottomNavigationBar.isVisible = false
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onNavigateUp()
    }
}