package app.tinks.weather.citylist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.tinks.weather.model.City
import app.tinks.weather.model.LiveWeather
import app.tinks.weather.ui.theme.WeatherTheme
import app.tinks.weather.utils.PreviewAnnotation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityItem(
    liveWeather: LiveWeather,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit = {}
) {
    OutlinedCard(modifier = modifier, onClick = { onItemClick(liveWeather.city.cityId) }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(liveWeather.city.name, style = MaterialTheme.typography.titleLarge)
                Text("${liveWeather.temperature} °C", style = MaterialTheme.typography.titleLarge)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(liveWeather.weather, style = MaterialTheme.typography.titleMedium)
                Text(
                    "${liveWeather.winddirection}风 ${liveWeather.windpower}级",
                    style = MaterialTheme.typography.titleMedium
                )
                Text("湿度 ${liveWeather.humidity}%", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

@PreviewAnnotation
@Composable
private fun CityItemPreview() {
    val sampleLiveWeather = LiveWeather(
        city = City("021", "310000", "上海"),
        temperature = 23,
        weather = "晴",
        winddirection = "西",
        windpower = "≤3",
        humidity = 82,
    )
    WeatherTheme {
        CityItem(sampleLiveWeather)
    }
}