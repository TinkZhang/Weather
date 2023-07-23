package app.tinks.weather

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    windowSizeClass: WindowSizeClass
): MyAppState {
    return remember(navController, coroutineScope, windowSizeClass) {
        MyAppState(
            navController = navController,
        )
    }
}

class MyAppState(
    val navController: NavHostController,
) {

}