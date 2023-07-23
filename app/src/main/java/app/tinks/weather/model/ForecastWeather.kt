package app.tinks.weather.model

import app.tinks.weather.network.dto.WeatherDto
import java.util.Date

data class ForecastWeather(
    val city: City,
    val reporttime: Date? = null,
    val casts: List<Cast> = emptyList()
)

data class Cast(
    val date: String,
    val dayInfo: Info,
    val nightInfo: Info,
)

data class Info(
    val weather: String,
    val temp: Int,
    val wind: String,
    val power: String,
)

fun app.tinks.weather.network.dto.Cast.toCast() = Cast(
    date = this.date,
    dayInfo = Info(
        weather = this.dayweather,
        temp = this.daytemp.toInt(),
        wind = this.daywind,
        power = this.daypower,
    ),
    nightInfo = Info(
        weather = this.nightweather,
        temp = this.nighttemp.toInt(),
        wind = this.nightwind,
        power = this.nightpower
    )
)

fun WeatherDto.toForecastWeather(city: City): ForecastWeather {
    val result = this.forecasts?.get(0)!!
    return ForecastWeather(city = city, casts = result.casts.map { it.toCast() })
}
