package com.example.fithprackotlin

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnUpdate: Button = findViewById(R.id.btn_update)
        recyclerView = findViewById(R.id.recyclerView)

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "data-database"
        ).build()

        adapter = DataAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        loadDataFromDb()

        btnUpdate.setOnClickListener {
            updateDataFromApi()
        }
    }

    private fun loadDataFromDb() {
        lifecycleScope.launch {
            val dataList = withContext(Dispatchers.IO) {
                db.productDao().getAll()
            }
            adapter.submitList(dataList)
        }
    }

    private fun updateDataFromApi() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getProducts()
                withContext(Dispatchers.IO) {
                    db.productDao().insertAll(response.products)
                }
                loadDataFromDb()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}