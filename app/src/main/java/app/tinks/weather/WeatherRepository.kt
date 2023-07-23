package app.tinks.weather

import app.tinks.weather.model.City
import app.tinks.weather.model.ForecastWeather
import app.tinks.weather.model.LiveWeather
import app.tinks.weather.model.toForecastWeather
import app.tinks.weather.model.toLiveWeather
import app.tinks.weather.network.WeatherApi
import app.tinks.weather.utils.getCity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

object WeatherRepository {
    private val api = WeatherApi
    val liveWeathers: MutableMap<String, LiveWeather> = AppConfig.cityList.associate {
        it.adCode to LiveWeather(it)
    }.toMutableMap()

    private val forecastWeathers: MutableMap<String, ForecastWeather> =
        AppConfig.cityList.associate {
            it.adCode to ForecastWeather(it)
        }.toMutableMap()


    suspend fun updateAllLiveWeathers() {
        withContext(Dispatchers.IO) {
            val liveRequests = AppConfig.cityList.map { city ->
                async { api.getLiveWeather(city.adCode) }
            }
            coroutineScope {
                val results = liveRequests.awaitAll()
                for (result in results) {
                    val adcode = result.lives?.get(0)?.adcode
                    if (!adcode.isNullOrEmpty()) {
                        liveWeathers[adcode] = result.toLiveWeather(getCity(adcode))!!
                    }
                }
            }
        }

    }

    suspend fun updateForecast(city: City) {
        val result = api.getForecastWeather(city.adCode).toForecastWeather(city)
        forecastWeathers[city.adCode] = result!!
    }
}