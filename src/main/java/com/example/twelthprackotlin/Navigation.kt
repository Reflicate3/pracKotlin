package com.example.twelthprackotlin


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.twelthprackotlin.Screen
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.ImageDownloader,
        Screen.SavedImages,
        Screen.About
    )
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        is Screen.ImageDownloader -> Icon(Icons.Default.ExitToApp, contentDescription = null)
                        is Screen.SavedImages -> Icon(Icons.Default.AccountCircle, contentDescription = null)
                        is Screen.About -> Icon(Icons.Default.Info, contentDescription = null)
                    }
                },
                label = { Text(screen.title) },
                selected = navController.currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState
) {
    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp)
        ) {
            Text(
                text = "Меню",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 8.dp))
            Spacer(modifier = Modifier.height(16.dp))
            NavigationDrawerItem(
                label = { Text("Загрузчик изображений") },
                selected = navController.currentDestination?.route == Screen.ImageDownloader.route,
                onClick = {
                    navController.navigate(Screen.ImageDownloader.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    coroutineScope.launch { drawerState.close() }
                },
                icon = { Icon(Icons.Default.ExitToApp, contentDescription = null) }
            )
            NavigationDrawerItem(
                label = { Text("Сохранённые изображения") },
                selected = navController.currentDestination?.route == Screen.SavedImages.route,
                onClick = {
                    navController.navigate(Screen.SavedImages.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    coroutineScope.launch { drawerState.close() }
                },
                icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) }
            )
            NavigationDrawerItem(
                label = { Text("О приложении") },
                selected = navController.currentDestination?.route == Screen.About.route,
                onClick = {
                    navController.navigate(Screen.About.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    coroutineScope.launch { drawerState.close() }
                },
                icon = { Icon(Icons.Default.Info, contentDescription = null) }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}