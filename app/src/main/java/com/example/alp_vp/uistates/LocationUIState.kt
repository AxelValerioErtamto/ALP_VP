package com.example.alp_vp.uistates

import com.example.alp_vp.models.LocationModel

sealed interface LocationUIState {
    data class Success(val locations: List<LocationModel>): LocationUIState
    object Loading: LocationUIState
    object Start: LocationUIState
    data class Failed(val errorMessage: String): LocationUIState
}