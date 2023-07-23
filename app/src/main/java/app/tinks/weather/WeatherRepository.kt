package app.tinks.weather

import app.tinks.weather.model.City
import app.tinks.weather.model.ForecastWeather
import app.tinks.weather.model.LiveWeather
import app.tinks.weather.model.toForecastWeather
import app.tinks.weather.network.WeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        CoroutineScope(Dispatchers.IO).launch {
//            AppConfig.cityList.forEach { city ->
//                liveWeathers[city.adCode] =
//                    withContext(Dispatchers.IO) {
//                        api.getLiveWeather(
//                            city.adCode
//                        ).toLiveWeather(city)
//                    }!!
//            }
            liveWeathers["310000"] =
                LiveWeather(city = City("021", "310000", "上海"),
                    temperature = 23,
                    weather = "晴",
                    winddirection = "西",
                    windpower = "≤3",
                    humidity = 82,
                )
        }
    }

    suspend fun updateForecast(city: City) {
        val result = api.getForecastWeather(city.adCode).toForecastWeather(city)
        forecastWeathers[city.adCode] = result!!
    }
}