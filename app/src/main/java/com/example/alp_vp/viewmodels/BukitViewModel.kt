package com.example.alp_vp.viewmodels

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

class BukitViewModel(
    private val locationRepository: LocationRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    var locationUIState: LocationUIState = LocationUIState.Start
        private set

    // Function to fetch all locations
    fun getAllBukits() {
        viewModelScope.launch {
            // Retrieve the token from UserRepository
            userRepository.currentUserToken.collect { token ->
                locationUIState = LocationUIState.Loading
                locationRepository.getAllLocations(token,
                    onResult = { locations ->
                        // Filter locations to only include "Bukit-" locations
                        val bukits = locations?.filter { location ->
                            location.nama.startsWith("Bukit-")
                        }

                        // Process the locations:
                        val bukitsWithModifiedNama = bukits?.map { location ->
                            // Extract the numerical part from "Bukit-<number>"
                            val newNama = location.nama.substringAfter("Bukit-").toIntOrNull()

                            // Create a new LocationModel with modified name and reordered id
                            newNama?.let {
                                LocationModel(
                                    id = it,  // Assign new id based on the number
                                    nama = it.toString(),  // Use the numerical value as nama
                                    isFilled = location.isFilled
                                )
                            }
                        }
                        // Sort by the numeric value of 'nama', then filter out nulls
                        val sortedBukits = bukitsWithModifiedNama
                            ?.sortedBy { it?.nama?.toIntOrNull() ?: Int.MAX_VALUE }
                            ?.mapIndexed { index, location ->
                                // Assign new IDs after sorting
                                location?.copy(id = index + 1)
                            }
                            ?.filterNotNull()  // Remove any null values if any

                        locationUIState = if (sortedBukits.isNullOrEmpty()) {
                            LocationUIState.Failed("No Bukit locations found")
                        } else {
                            LocationUIState.Success(sortedBukits) // No null values here
                        }
                    },
                    onError = { error ->
                        locationUIState = LocationUIState.Failed(error)
                    }
                )
            }
        }
    }

    fun getBukitColors(): List<Color> {
        return (locationUIState as? LocationUIState.Success)?.locations?.map { location ->
            if (location.isFilled) {
                Color.Red
            } else {
                Color.Green
            }
        } ?: emptyList()
    }

    fun getBukitNama(): List<String> {
        return (locationUIState as? LocationUIState.Success)?.locations?.map { location ->
            location.nama
        } ?: emptyList()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ParkhubApplication)
                val locationRepository = application.container.locationRepository
                val userRepository = application.container.userRepository
                BukitViewModel(locationRepository, userRepository)  // Injecting LocationRepository
            }
            }
        }
}