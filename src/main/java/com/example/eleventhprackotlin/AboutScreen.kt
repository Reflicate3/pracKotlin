package com.example.eleventhprackotlin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "О приложении",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Это приложение позволяет загружать изображения по URL и сохранять их во внутреннюю память устройства. Вы можете просматривать загруженные изображения и управлять ими.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}