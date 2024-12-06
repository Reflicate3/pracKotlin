package com.example.secondprackotlin

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class NavigationApiActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_api)

        // Найти NavController по id контейнера
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        findViewById<Button>(R.id.nextButton).setOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.firstFragment -> navController.navigate(R.id.action_firstFragment_to_secondFragment)
                R.id.secondFragment -> navController.navigate(R.id.action_secondFragment_to_thirdFragment)
            }
        }

        findViewById<Button>(R.id.prevButton).setOnClickListener {
            navController.popBackStack()
        }
    }
}

