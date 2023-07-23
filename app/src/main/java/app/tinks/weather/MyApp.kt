package app.tinks.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.tinks.weather.navigation.MyNavHost

@Composable
fun MyApp(
    windowSizeClass: WindowSizeClass,
    appState: MyAppState = rememberAppState(windowSizeClass = windowSizeClass)
) {
    Column(Modifier.fillMaxSize()) {
        MyNavHost(
            navController = appState.navController,
        )
    }
}