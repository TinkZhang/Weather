package app.tinks.weather.citydetail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun CityDetailRoute(
    navController: NavController,
    cityId: String?
) {
    CityDetailScreen(city = cityId ?: "no city",
        onClickBack = { navController.popBackStack() })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailScreen(
    city: String,
    onClickBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(title = { Text(city) }, navigationIcon = {
                IconButton(onClick = onClickBack) {
                    Icon(Icons.Default.ArrowBack, "Back")
                }
            })
        },
    ) {
        Text("Sunny", modifier = Modifier.padding(it))
    }
}