package app.tinks.weather.model

import app.tinks.weather.network.dto.WeatherDto
import java.util.Date

data class LiveWeather(
    val city: City,
    val weather: String = "",
    val temperature: Int = 0,
    val winddirection: String = "",
    val windpower: String = "",
    val humidity: Int = 0,
    val reporttime: Date? = null,
)

fun WeatherDto.toLiveWeather(city: City): LiveWeather? =
    this.lives?.get(0)?.let {
        LiveWeather(
            city = city,
            weather = it.weather,
            temperature = it.temperature.toInt(),
            winddirection = it.winddirection,
            windpower = it.windpower,
            humidity = it.humidity.toInt(),
            reporttime = null
        )
    }