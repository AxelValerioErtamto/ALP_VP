package com.example.alp_vp.services

import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.GetAllReportResponse
import com.example.alp_vp.models.ReportRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ReportAPIService {
    @GET("api/reports")
    fun getAllReports(
        @Header("X-API-TOKEN") token: String,
    ): Call<GetAllReportResponse>

    @POST("api/reports")
    fun createReport(
        @Header("X-API-TOKEN") token: String,
        @Body reportModel: ReportRequest
    ): Call<GeneralResponseModel>
}
