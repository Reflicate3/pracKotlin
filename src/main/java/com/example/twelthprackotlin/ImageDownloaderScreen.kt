package com.example.twelthprackotlin

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Data
import com.example.twelthprackotlin.utils.downloadImage
import com.example.twelthprackotlin.utils.saveImage

@Composable
fun ImageDownloaderScreen() {
    var urlInput by remember { mutableStateOf("") }
    val images = remember { mutableStateListOf<Bitmap>() }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Поле для ввода URL
            TextField(
                value = urlInput,
                onValueChange = { urlInput = it },
                label = { Text("Введите URL изображения") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Кнопка для загрузки изображения
            Button(
                onClick = {
                    if (urlInput.isNotEmpty()) {
                        coroutineScope.launch {
                            val bitmap = downloadImage(urlInput)
                            if (bitmap != null) {
                                images.add(0, bitmap) // Добавляем изображение в начало списка
                                Toast.makeText(context, "Изображение загружено", Toast.LENGTH_SHORT)
                                    .show()
                                saveImage(context, bitmap)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Ошибка загрузки изображения",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Введите URL", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Загрузить изображение")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Кнопка для запуска WorkManager
            Button(
                onClick = {
                    if (urlInput.isNotEmpty()) {
                        val workManager = WorkManager.getInstance(context)
                        val data = Data.Builder()
                            .putString("image_url", urlInput)
                            .build()
                        val workRequest = OneTimeWorkRequestBuilder<ImageDownloadWorker>()
                            .setInputData(data)
                            .build()
                        workManager.enqueue(workRequest)
                        Toast.makeText(context, "Загрузка изображения в фоне", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "Введите URL", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Загрузить в фоне")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Отображение списка загруженных изображений
        if (images.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Распределяем оставшееся пространство
                    .padding(horizontal = 16.dp) // Отступы по бокам
            ) {
                items(images) { bitmap ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Image(
                            bitmap = bitmap.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("Нет загруженных изображений")
            }
        }
    }
}