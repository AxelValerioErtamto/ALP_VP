package com.example.alp_vp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alp_vp.repositories.LocationRepository
import com.example.alp_vp.models.LocationModel
import com.example.alp_vp.models.UpdateLocationModel
import com.example.alp_vp.uistates.LocationUIState
import kotlinx.coroutines.launch

class LocationViewModel(private val locationRepository: LocationRepository) : ViewModel() {

    // LiveData or State for the location list and error handling
    var locations: List<LocationModel>? = null
    var errorMessage: String? = null

    var locationUIState: LocationUIState = LocationUIState.Start
        private set

    // Function to fetch all locations
    fun getAllLocations(token: String) {
        locationUIState = LocationUIState.Loading
        viewModelScope.launch {
            locationRepository.getAllLocations(token,
                onResult = { locations ->
                    locationUIState = if (locations.isNullOrEmpty()) {
                        LocationUIState.Failed("No locations found")
                    } else {
                        LocationUIState.Success(locations)
                    }
                },
                onError = { error ->
                    locationUIState = LocationUIState.Failed(error)
                }
            )
        }
    }

    // Function to update a location
    fun updateLocation(token: String, locationId: Int, updatedLocation: UpdateLocationModel) {
        viewModelScope.launch {
            locationRepository.updateLocation(token, locationId, updatedLocation,
                onResult = { updatedLoc ->
                    // Handle updated location if necessary (e.g., update locations list)
                    updatedLoc?.let {
                        // Do something with the updated location
                    }
                },
                onError = { error ->
                    errorMessage = error
                }
            )
        }
    }
}


