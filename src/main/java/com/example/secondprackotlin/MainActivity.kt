package com.example.secondprackotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manualNavButton: Button = findViewById(R.id.manualNavButton)
        val navigationApiButton: Button = findViewById(R.id.navigationApiButton)

        manualNavButton.setOnClickListener {
            val intent = Intent(this, ManualNavigationActivity::class.java)
            startActivity(intent)
        }

        navigationApiButton.setOnClickListener {
            val intent = Intent(this, NavigationApiActivity::class.java)
            startActivity(intent)
        }
    }
}