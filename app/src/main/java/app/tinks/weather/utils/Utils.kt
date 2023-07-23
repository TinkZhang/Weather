package app.tinks.weather.utils

import app.tinks.weather.AppConfig
import app.tinks.weather.model.City

fun getCity(adCode: String): City {
    return AppConfig.cityList.first { it.adCode == adCode }
}
