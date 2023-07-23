package app.tinks.weather.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    val status: String,
    val infocode: String,

    @SerializedName("lives")
    val lives: List<Live>?,

    @SerializedName("forecasts")
    val forecasts: List<Forecast>?,

)

data class Live(
    val weather: String,
    val temperature: String,
    val winddirection: String,
    val windpower: String,
    val humidity: String,
    val reporttime: String,
)

data class Forecast(
    val reporttime: String,
    @SerializedName("casts")
    val casts: List<Cast>,
)

data class Cast(
    val date: String,
    val week: String,
    val dayweather: String,
    val nightweather: String,
    val daytemp: String,
    val nighttemp: String,
    val daywind: String,
    val nightwind: String,
    val daypower: String,
    val nightpower: String
)
