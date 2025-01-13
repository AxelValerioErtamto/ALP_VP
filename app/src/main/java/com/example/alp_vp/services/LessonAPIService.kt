package com.example.alp_vp.services

import com.example.alp_vp.models.LessonModel
import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.GetAllLessonResponse
import com.example.alp_vp.models.GetLessonResponse
import com.example.alp_vp.models.LessonRequest
import com.example.alp_vp.models.UploadImageResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface LessonAPIService {
    @Multipart
    @POST("/upload") // Your upload endpoint
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): Response<GeneralResponseModel> // Your response model

    @GET("/api/lessons")
    fun getAllLessons(@Header("X-API-TOKEN") token: String): Call<GetAllLessonResponse>

    @GET("/api/lessons/{id}")
    fun getLesson(@Header("X-API-TOKEN") token: String, @Path("id") id: Int): Call<GetLessonResponse>

    @POST("/api/lessons")
    fun createLesson(@Header("X-API-TOKEN") token: String, @Body lessonModel: LessonRequest): Call<GeneralResponseModel>
}
