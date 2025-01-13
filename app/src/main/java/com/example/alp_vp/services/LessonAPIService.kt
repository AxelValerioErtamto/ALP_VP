package com.example.alp_vp.services

import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.GetLessonResponse
import com.example.alp_vp.models.LessonModel
import com.example.alp_vp.models.LessonRequest
import retrofit2.Call
import retrofit2.http.*

interface LessonAPIService {

    @GET("/api/lessons")
    fun getAllLessons(@Header("X-API-TOKEN") token: String): Call<List<LessonModel>>

    @GET("/api/lessons/{lessonId}")
    fun getLesson(@Header("X-API-TOKEN") token: String, @Path("lessonId") id: Int): Call<GetLessonResponse>

    @GET("/api/lessons/details/{id}")
    fun getLessonDetails(
        @Header("X-API-TOKEN") token: String,
        @Path("id") id: Int
    ): Call<LessonModel>

    @POST("/api/lessons")
    fun createLesson(@Header("X-API-TOKEN") token: String, @Body lessonModel: LessonRequest): Call<GeneralResponseModel>

    @PUT("/api/lessons/{id}")
    fun updateLesson(
        @Header("X-API-TOKEN") token: String,
        @Path("id") id: Int,
        @Body lessonModel: LessonRequest
    ): Call<GeneralResponseModel>

    @DELETE("/api/lessons/{id}")
    fun deleteLesson(
        @Header("X-API-TOKEN") token: String,
        @Path("id") id: Int
    ): Call<GeneralResponseModel>

}
