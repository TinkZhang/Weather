package app.tinks.weather.citylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.tinks.weather.WeatherRepository
import app.tinks.weather.model.LiveWeather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CityListViewModel : ViewModel() {
    private val repository = WeatherRepository

    private val _uiState =
        MutableStateFlow(ListUiState(isLoading = false, liveWeathers = repository.liveWeathers))
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()


    fun update() {
        viewModelScope.launch {
            repository.updateAllLiveWeathers()
            _uiState.value = ListUiState(isLoading = true, liveWeathers = repository.liveWeathers)
        }
    }
}

data class ListUiState(
    val isLoading: Boolean = false,
    val liveWeathers: Map<String, LiveWeather>
)