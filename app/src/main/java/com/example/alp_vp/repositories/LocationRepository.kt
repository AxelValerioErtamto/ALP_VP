package com.example.alp_vp.repositories

import com.example.alp_vp.models.LocationModel
import com.example.alp_vp.models.UpdateLocationModel
import com.example.alp_vp.services.LocationAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface LocationRepository {
    fun getAllLocations(token: String, onResult: (List<LocationModel>?) -> Unit, onError: (String) -> Unit)
    fun updateLocation(
        token: String,
        locationId: Int,
        location: UpdateLocationModel,
        onResult: (LocationModel?) -> Unit,
        onError: (String) -> Unit
    )
}

class NetworkLocationRepository(private val locationAPIService: LocationAPIService) : LocationRepository {
    override fun getAllLocations(token: String, onResult: (List<LocationModel>?) -> Unit, onError: (String) -> Unit) {
        locationAPIService.getAllLocations(token).enqueue(object : Callback<List<LocationModel>> {
            override fun onResponse(call: Call<List<LocationModel>>, response: Response<List<LocationModel>>) {
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    onError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<LocationModel>>, t: Throwable) {
                onError(t.message ?: "An error occurred")
            }
        })
    }

    override fun updateLocation(
        token: String,
        locationId: Int,
        location: UpdateLocationModel,
        onResult: (LocationModel?) -> Unit,
        onError: (String) -> Unit
    ) {
        locationAPIService.updateLocation(token, locationId, location)
            .enqueue(object : Callback<LocationModel> {
                override fun onResponse(call: Call<LocationModel>, response: Response<LocationModel>) {
                    if (response.isSuccessful) {
                        onResult(response.body())
                    } else {
                        onError("Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<LocationModel>, t: Throwable) {
                    onError(t.message ?: "An error occurred")
                }
            })
    }
}

// Mock implementation of LocationRepository
class MockLocationRepository : LocationRepository {
    override fun getAllLocations(
        token: String,
        onResult: (List<LocationModel>?) -> Unit,
        onError: (String) -> Unit
    ) {
        val mockBukit = List(36) { index ->
            LocationModel(
                id = index + 1,  // Unique ID for each location
                nama = "Bukit-${index + 1}",
                isFilled = index % 3 == 1 // Alternating true/false for the isFilled status
            )
        }

        val mockLapangan = List(281) { index ->
            LocationModel(
                id = index + 1,  // Unique ID for each location
                nama = "Lapangan-${index + 1}",
                isFilled = index % 3 == 1 // Alternating true/false for the isFilled status
            )
        }

        val mutableMockGedung = mutableListOf<LocationModel>()
        var idCounter = 1
        for (floor in 3..14) {
            val spots = if (floor % 2 == 0) 36 else 39
            for (spot in 1..spots) {
                mutableMockGedung.add(
                    LocationModel(
                        id = idCounter++,
                        nama = "Gedung-$floor-$spot",
                        isFilled = (spot + floor) % 3 == 1 // Alternating true/false based on floor and spot
                    )
                )
            }
        }

        val mockGedung: List<LocationModel> = mutableMockGedung.toList()

        // Simulate successful response with the mock data
        onResult(mockLapangan)
    }

    override fun updateLocation(
        token: String,
        locationId: Int,
        location: UpdateLocationModel,
        onResult: (LocationModel?) -> Unit,
        onError: (String) -> Unit
    ) {
        // Simulate a successful update (return null for now)
        onResult(null)
    }
}