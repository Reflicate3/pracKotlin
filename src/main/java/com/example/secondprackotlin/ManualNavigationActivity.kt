package com.example.secondprackotlin

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ManualNavigationActivity : AppCompatActivity() {
    private val fragments = listOf(FirstFragment(), SecondFragment(), ThirdFragment())
    private var currentFragmentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_navigation)

        // Load initial fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragments[currentFragmentIndex])
            .commit()

        findViewById<Button>(R.id.nextButton).setOnClickListener {
            if (currentFragmentIndex < fragments.size - 1) {
                currentFragmentIndex++
                replaceFragment()
            }
        }

        findViewById<Button>(R.id.prevButton).setOnClickListener {
            if (currentFragmentIndex > 0) {
                currentFragmentIndex--
                replaceFragment()
            }
        }
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragments[currentFragmentIndex])
            .addToBackStack(null)
            .commit()
    }
}