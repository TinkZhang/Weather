package app.tinks.weather.model

import app.tinks.weather.network.dto.WeatherDto
import java.util.Date

data class ForecastWeather(
    val city: City,
    val weather: String = "",
    val temperature: Int = 0,
    val winddirection: String = "",
    val windpower: String = "",
    val humidity: Int = 0,
    val reporttime: Date? = null,
)

fun WeatherDto.toForecastWeather(city: City): ForecastWeather? =
    this.forecasts?.get(0)?.casts?.get(0)?.let {
        ForecastWeather(
        city = city,
        weather = it.dayweather
    )
    }