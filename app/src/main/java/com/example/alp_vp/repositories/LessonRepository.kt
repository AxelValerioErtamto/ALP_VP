package com.example.alp_vp.repositories

import com.example.alp_vp.enums.PrioritiesEnum
import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.GetAllLessonResponse
import com.example.alp_vp.models.GetLessonResponse
import com.example.alp_vp.models.LessonRequest
import com.example.alp_vp.services.LessonAPIService
import retrofit2.Call

interface LessonRepository {
    fun getAllLessons(token: String): Call<GetAllLessonResponse>
    fun createLesson(token: String, title: String, description: String, content: String, imageUri: String): Call<GeneralResponseModel>
    fun getLesson(token: String, lessonId: Int): Call<GetLessonResponse>
}

class NetworkLessonRepository(
    private val lessonAPIService: LessonAPIService
) : LessonRepository {

    override fun createLesson(
        token: String,
        title: String,
        description: String,
        content: String,
        imageUri: String
    ): Call<GeneralResponseModel> {
        return lessonAPIService.createLesson(
            token,
            LessonRequest(title,description,content,imageUri)
        )
    }

    override fun getAllLessons(token: String): Call<GetAllLessonResponse> {
        return lessonAPIService.getAllLessons(token)
    }

    override fun getLesson(token: String, lessonId: Int): Call<GetLessonResponse> {
        return lessonAPIService.getLesson(token, lessonId)
    }
}
