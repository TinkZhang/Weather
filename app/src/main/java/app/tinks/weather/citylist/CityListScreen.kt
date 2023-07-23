package app.tinks.weather.citylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import app.tinks.weather.AppConfig
import app.tinks.weather.navigation.navigateToCity
import app.tinks.weather.network.WeatherApi

@Composable
fun CityListRoute(
    navController: NavController
) {
    CityListScreen(onItemClicked = { navController.navigateToCity(it) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(
    onItemClicked: (String) -> Unit,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text("City List") })
    }) { padding ->
        LaunchedEffect(key1 = 1) {
            WeatherApi.getLiveWeather(" ")
        }
        LazyColumn(Modifier.padding(padding)) {
            items(items = AppConfig.cityList, key = { it.cityId }) { city ->
                Text(
                    city.name, modifier = Modifier.clickable { onItemClicked(city.name) }
                )
            }
        }
    }
}