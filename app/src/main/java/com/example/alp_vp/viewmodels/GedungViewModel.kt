package com.example.alp_vp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.alp_vp.ParkhubApplication
import com.example.alp_vp.models.LocationModel
import com.example.alp_vp.repositories.LocationRepository
import com.example.alp_vp.repositories.UserRepository
import com.example.alp_vp.uistates.LocationUIState
import kotlinx.coroutines.launch

class GedungViewModel(
    private val locationRepository: LocationRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var locationUIState: LocationUIState = LocationUIState.Start
        private set

    var currentFloor by mutableIntStateOf(3) // Current floor state

    // Function to increment the floor
    fun incrementFloor() {
        if (currentFloor < 14) {
            currentFloor++
        }
    }

    // Function to decrement the floor
    fun decrementFloor() {
        if (currentFloor > 3) {
            currentFloor--
        }
    }

    // Function to fetch all "Gedung" locations
    fun getAllGedungs() {
        viewModelScope.launch {
            userRepository.currentUserToken.collect { token ->
                locationUIState = LocationUIState.Loading
                locationRepository.getAllLocations(token,
                    onResult = { locations ->
                        // Filter and parse locations with "Gedung" prefix
                        val gedungs = locations?.filter { location ->
                            location.nama.startsWith("Gedung-")
                        }?.map { location ->
                            val parts = location.nama.split("-")
                            if (parts.size == 3) {
                                val floor = parts[1].toIntOrNull()
                                val spot = parts[2].toIntOrNull()
                                if (floor != null && spot != null) {
                                    LocationModel(
                                        id = floor * 100 + spot, // Unique ID for sorting
                                        nama = location.nama,
                                        isFilled = location.isFilled
                                    )
                                } else null
                            } else null
                        }?.filterNotNull()

                        // Group by floor and sort by spot
                        val groupedGedungs = gedungs?.groupBy { location ->
                            location.nama.split("-")[1].toInt()
                        }?.mapValues { (_, spots) ->
                            spots.sortedBy { spot ->
                                spot.nama.split("-")[2].toInt()
                            }
                        }

                        locationUIState = if (groupedGedungs.isNullOrEmpty()) {
                            LocationUIState.Failed("No Gedung locations found")
                        } else {
                            LocationUIState.Success(groupedGedungs.flatMap { it.value })
                        }
                    },
                    onError = { error ->
                        locationUIState = LocationUIState.Failed(error)
                    }
                )
            }
        }
    }

    fun getGedungColor(isFilled: Boolean): Color {
        return if (isFilled) Color.Red else Color.Green
    }

    // Get names of spots in the current floor
    fun getGedungNama(): List<String> {
        return (locationUIState as? LocationUIState.Success)?.locations?.map { location ->
            location.nama
        } ?: emptyList()
    }

    fun getAllGedungList(): List<LocationModel> {
        return (locationUIState as? LocationUIState.Success)?.locations ?: emptyList()
    }

    fun isEven(currentFloor: Int): Boolean {
        return currentFloor % 2 == 0
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ParkhubApplication)
                val locationRepository = application.container.locationRepository
                val userRepository = application.container.userRepository
                GedungViewModel(locationRepository, userRepository)  // Injecting LocationRepository
            }
        }
    }
}
