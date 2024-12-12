package com.example.sixthprackotlin

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var productDao: ProductDao

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnUpdate: Button = findViewById(R.id.btn_update)
        recyclerView = findViewById(R.id.recyclerView)

        // Настройка RecyclerView
        adapter = DataAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Загрузка данных из базы данных и отображение
        loadDataFromDb()

        btnUpdate.setOnClickListener {
            updateDataFromApi()
        }
    }

    private fun loadDataFromDb() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataList = productDao.getAll()
            withContext(Dispatchers.Main) {
                adapter.submitList(dataList)
            }
        }
    }

    private fun updateDataFromApi() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getProducts()
                productDao.insertAll(response.products)
                loadDataFromDb() // Перезагрузим данные
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
