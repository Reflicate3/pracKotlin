package com.example.seventhprackotlin

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var urlInput: EditText
    private lateinit var downloadButton: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlInput = findViewById(R.id.urlInput)
        downloadButton = findViewById(R.id.downloadButton)
        imageView = findViewById(R.id.imageView)

        downloadButton.setOnClickListener {
            val imageUrl = urlInput.text.toString()
            if (imageUrl.isNotEmpty()) {
                downloadImage(imageUrl)
            } else {
                Toast.makeText(this, "Введите URL", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun downloadImage(url: String) {
        lifecycleScope.launch {
            try {
                val imageBytes = withContext(Dispatchers.IO) {
                    val inputStream: InputStream = URL(url).openStream()
                    inputStream.readBytes()
                }


                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)


                imageView.setImageBitmap(bitmap)
                imageView.visibility = ImageView.VISIBLE


                withContext(Dispatchers.IO) {
                    saveImage(imageBytes)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Ошибка загрузки изображения", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun saveImage(imageBytes: ByteArray, directory: File = filesDir): Boolean {
        return try {
            val file = File(directory, "downloaded_image.jpg")
            FileOutputStream(file).use { fos ->
                fos.write(imageBytes)
            }

            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Изображение сохранено", Toast.LENGTH_SHORT).show()
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Ошибка сохранения изображения", Toast.LENGTH_SHORT).show()
            }
            false
        }
    }
}
