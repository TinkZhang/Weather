package app.tinks.weather.citydetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.tinks.weather.citydetail.ui.DayItem

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
    viewModel: CityDetailViewModel = viewModel(),
    onClickBack: () -> Unit = {}
) {
    LaunchedEffect(key1 = true) {
        viewModel.update()
    }
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(uiState.value.forecastWeather.city.name) },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                })
        },
    ) { padding ->
        val casts = uiState.value.forecastWeather.casts
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = casts) {
                DayItem(cast = it, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}