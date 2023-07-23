package app.tinks.weather.citylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.tinks.weather.citylist.ui.CityItem
import app.tinks.weather.model.LiveWeather
import app.tinks.weather.navigation.navigateToCity

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
    viewModel: CityListViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = 1) {
        viewModel.update()
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(if (uiState.value.isLoading) "Loading" else "City List") })
    }) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items = uiState.value.liveWeathers.values.toList()) { liveWeather: LiveWeather ->
                CityItem(liveWeather, onItemClick = onItemClicked)
            }
        }
    }
}
