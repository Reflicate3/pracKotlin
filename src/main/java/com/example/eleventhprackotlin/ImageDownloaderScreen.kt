package com.example.eleventhprackotlin

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
// Добавьте этот импорт
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
import com.example.eleventhprackotlin.utils.downloadImage
import com.example.eleventhprackotlin.utils.saveImage
import kotlinx.coroutines.launch

@Composable
fun ImageDownloaderScreen() {
    var urlInput by remember { mutableStateOf("") }
    val images = remember { mutableStateListOf<Bitmap>() }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
        // Отступы будут применены в MainApp через innerPadding
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
                // Убедитесь, что используете правильную функцию items
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