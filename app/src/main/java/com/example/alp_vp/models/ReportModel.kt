
package com.example.alp_vp.models

import com.google.gson.annotations.SerializedName

data class GetAllReportResponse(
    val data: List<ReportModel>
)

data class ReportModel(
    val id: Int = 0,
    val userId: Int = 0,
    val title: String = "",
    val description: String = "",
    @SerializedName("image_uri")
    val image: String = ""
)

data class ReportRequest(
    val userId: Int = 0,
    val title: String = "",
    val description: String = "",
    @SerializedName("image_uri")
    val image: String = ""

)