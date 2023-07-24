package app.tinks.weather.citydetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tinks.weather.WeatherRepository
import app.tinks.weather.model.ForecastWeather
import app.tinks.weather.utils.getCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityDetailViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val city = getCity(savedStateHandle["cityId"]!!)

    private val repository = WeatherRepository

    private val _uiState =
        MutableStateFlow(
            DetailUiState(
                isLoading = false,
                forecastWeather = repository.getForecastWeathers(city.adCode)
            )
        )
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()


    fun update() {
        _uiState.value = _uiState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                repository.updateForecast(city.adCode)
                _uiState.value = DetailUiState(
                    isLoading = false,
                    isError = false,
                    forecastWeather = repository.getForecastWeathers(city.adCode)
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isError = true)
            }
        }
    }
}

data class DetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val forecastWeather: ForecastWeather
)