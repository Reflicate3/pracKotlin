package com.example.twelthprackotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.twelthprackotlin.ui.theme.TwelthPracKotlinTheme
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String, val title: String) {
    object ImageDownloader : Screen("image_downloader", "Загрузчик изображений")
    object SavedImages : Screen("saved_images", "Сохранённые изображения")
    object About : Screen("about", "О приложении")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TwelthPracKotlinTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(navController, coroutineScope, drawerState)
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                val currentDestination = navController.currentBackStackEntryAsState().value?.destination
                TopAppBar(
                    title = {
                        Text(currentDestination?.route?.let { getTitleByRoute(it) } ?: "Приложение")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Меню")
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigationBar(navController)
            },
            content = { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.ImageDownloader.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.ImageDownloader.route) {
                        ImageDownloaderScreen()
                    }
                    composable(Screen.SavedImages.route) {
                        SavedImagesScreen()
                    }
                    composable(Screen.About.route) {
                        AboutScreen()
                    }
                }
            }
        )
    }
}

fun getTitleByRoute(route: String): String {
    return when (route) {
        Screen.ImageDownloader.route -> Screen.ImageDownloader.title
        Screen.SavedImages.route -> Screen.SavedImages.title
        Screen.About.route -> Screen.About.title
        else -> "Приложение"
    }
}