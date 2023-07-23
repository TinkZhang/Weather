package app.tinks.weather.citydetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.tinks.weather.model.Cast
import app.tinks.weather.model.Info
import app.tinks.weather.ui.theme.WeatherTheme
import app.tinks.weather.utils.PreviewAnnotation

@Composable
fun DayItem(
    cast: Cast,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row() {
                Text(
                    cast.date,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Text(
                "${cast.dayInfo.temp}°C - ${cast.nightInfo.temp}°C",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                if (cast.dayInfo.weather != cast.nightInfo.weather)
                    "${cast.dayInfo.weather} 转 ${cast.nightInfo.weather}"
                else cast.nightInfo.weather,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                if (cast.dayInfo.wind != cast.nightInfo.wind || cast.dayInfo.power != cast.nightInfo.power)
                    "${cast.dayInfo.wind}风${cast.dayInfo.power}级 转 ${cast.nightInfo.wind}风${cast.nightInfo.power}级"
                else "${cast.dayInfo.wind}风${cast.dayInfo.power}级"
            )
        }
    }
}

@PreviewAnnotation
@Composable
private fun DayItemPreview() {
    val sample = Cast(
        date = "2023-09-18",
        dayInfo = Info("晴", 34, "西", "3"),
        nightInfo = Info("多云", 24, "西", "2")
    )
    WeatherTheme() {
        DayItem(cast = sample)
    }
}