package com.example.thirdpracremaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import android.widget.Button

class NavigationApiActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_api)

        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        findViewById<Button>(R.id.nextButton).setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.inputFragment1 -> navController.navigate(R.id.action_inputFragment1_to_inputFragment2)
                R.id.inputFragment2 -> navController.navigate(R.id.action_inputFragment2_to_inputFragment1)
            }
        }
    }
}
