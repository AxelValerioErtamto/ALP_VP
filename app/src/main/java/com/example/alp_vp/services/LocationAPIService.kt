package com.example.alp_vp.services

import com.example.alp_vp.models.LocationModel
import com.example.alp_vp.models.UpdateLocationModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface LocationAPIService {
    @GET("api/locations")
    fun getAllLocations(@Header("X-API-TOKEN") token: String): Call<List<LocationModel>>

    @PUT("api/locations/{locationId}")
    fun updateLocation(
        @Header("X-API-TOKEN") token: String,
        @Path("locationId") locationId: Int,
        @Body location: UpdateLocationModel
    ): Call<LocationModel>
}