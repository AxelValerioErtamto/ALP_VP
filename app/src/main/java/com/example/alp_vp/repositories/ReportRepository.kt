package com.example.alp_vp.repositories

import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.GetAllReportResponse
import com.example.alp_vp.models.ReportRequest
import com.example.alp_vp.services.ReportAPIService
import retrofit2.Call

// Define the repository interface
interface ReportRepository {
    fun getAllReports(token: String): Call<GetAllReportResponse>
    fun createReport(token: String, userId:Int, title:String, description:String, image:String): Call<GeneralResponseModel>
}

// Implementation of the repository using the API service
class NetworkReportRepository(
    private val reportAPIService: ReportAPIService
) : ReportRepository {
    override fun getAllReports(token: String): Call<GetAllReportResponse> {
        return reportAPIService.getAllReports(token)
    }

    override fun createReport(
        token: String,
        userId: Int,
        title: String,
        description: String,
        image: String
    ): Call<GeneralResponseModel> {
        return reportAPIService.createReport(token, ReportRequest(userId, title,description,image))
    }
}
