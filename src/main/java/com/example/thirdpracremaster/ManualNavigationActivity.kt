package com.example.thirdpracremaster

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class ManualNavigationActivity : AppCompatActivity() {
    private val fragments = listOf(DisplayFragment1(), DisplayFragment2())
    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manual_navigation)

        viewModel.currentFragmentIndex.observe(this, Observer { index ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragments[index])
                .commit()
            Log.d("ManualNavigationActivity", "Current fragment index: $index")
        })

        findViewById<Button>(R.id.nextButton).setOnClickListener {
            viewModel.nextFragment(fragments.size - 1)
        }

        findViewById<Button>(R.id.prevButton).setOnClickListener {
            viewModel.previousFragment()
        }
    }
}
