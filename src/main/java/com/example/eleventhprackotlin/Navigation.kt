package com.example.eleventhprackotlin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun BottomNavigationBar(currentScreen: Screen, onScreenSelected: (Screen) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.ExitToApp, contentDescription = null) },
            label = { Text("Загрузчик") },
            selected = currentScreen == Screen.ImageDownloader,
            onClick = { onScreenSelected(Screen.ImageDownloader) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountBox, contentDescription = null) },
            label = { Text("Галерея") },
            selected = currentScreen == Screen.SavedImages,
            onClick = { onScreenSelected(Screen.SavedImages) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = null) },
            label = { Text("О приложении") },
            selected = currentScreen == Screen.About,
            onClick = { onScreenSelected(Screen.About) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
    currentScreen: Screen,
    onScreenSelected: (Screen) -> Unit
) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp) // Добавляем отступы сверху и снизу
        ) {
            Text(
                text = "Меню",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Spacer(modifier = Modifier.height(16.dp)) // Отступ перед элементами меню
            NavigationDrawerItem(
                label = { Text("Загрузчик изображений") },
                selected = currentScreen == Screen.ImageDownloader,
                onClick = { onScreenSelected(Screen.ImageDownloader) },
                icon = { Icon(Icons.Default.ExitToApp, contentDescription = null) }
            )
            NavigationDrawerItem(
                label = { Text("Сохранённые изображения") },
                selected = currentScreen == Screen.SavedImages,
                onClick = { onScreenSelected(Screen.SavedImages) },
                icon = { Icon(Icons.Default.AccountBox, contentDescription = null) }
            )
            NavigationDrawerItem(
                label = { Text("О приложении") },
                selected = currentScreen == Screen.About,
                onClick = { onScreenSelected(Screen.About) },
                icon = { Icon(Icons.Default.Info, contentDescription = null) }
            )
            Spacer(modifier = Modifier.weight(1f)) // Отталкиваем элементы вверх, оставляя пространство снизу
        }
    }
}