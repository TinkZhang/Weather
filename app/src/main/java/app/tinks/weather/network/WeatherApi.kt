package app.tinks.weather.network

import app.tinks.weather.network.dto.WeatherDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://restapi.amap.com/"
const val KEY = "fec7592471b34f1167d92cc693982c3c"

object WeatherApi {
    private val client = OkHttpClient.Builder()
        .apply {
            if (true) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            connectTimeout(2, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
        }
        .build()


    private val service: WeatherService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)

    }

    suspend fun getForecastWeather(city: String): WeatherDto {
        return service.getForecastWeather("110101")
    }

    suspend fun getLiveWeather(city: String): WeatherDto {
        return service.getLiveWeather("110101")
    }
}

interface WeatherService {
    @GET("v3/weather/weatherInfo")
    suspend fun getForecastWeather(
        @Query("city") cityId: String,
        @Query("extensions") extensions: String = "all",
        @Query("key") key: String = KEY
    ): WeatherDto

    @GET("v3/weather/weatherInfo")
    suspend fun getLiveWeather(
        @Query("city") cityId: String,
        @Query("extensions") extensions: String = "base",
        @Query("key") key: String = KEY
    ): WeatherDto
}