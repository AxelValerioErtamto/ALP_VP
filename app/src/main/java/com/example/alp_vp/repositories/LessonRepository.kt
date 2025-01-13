package com.example.alp_vp.repositories

import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.GetLessonResponse
import com.example.alp_vp.models.LessonModel
import com.example.alp_vp.models.LessonRequest
import com.example.alp_vp.services.LessonAPIService
import retrofit2.Call

interface LessonRepository {
    fun getAllLessons(token: String): Call<List<LessonModel>>
    fun createLesson(token: String, title: String, description: String, content: String): Call<GeneralResponseModel>
    fun getLesson(token: String, lessonId: Int): Call<GetLessonResponse> // This will also handle fetching details
    fun updateLesson(token: String, lessonId: Int, title: String, description: String, content: String): Call<GeneralResponseModel>
    fun deleteLesson(token: String, lessonId: Int): Call<GeneralResponseModel>
}

class NetworkLessonRepository(
    private val lessonAPIService: LessonAPIService
) : LessonRepository {

    override fun getAllLessons(token: String): Call<List<LessonModel>> {
        return lessonAPIService.getAllLessons(token)
    }

    override fun createLesson(
        token: String,
        title: String,
        description: String,
        content: String
    ): Call<GeneralResponseModel> {
        return lessonAPIService.createLesson(
            token,
            LessonRequest(title, description, content)
        )
    }

    override fun getLesson(token: String, lessonId: Int): Call<GetLessonResponse> {
        return lessonAPIService.getLesson(token, lessonId)
    }

    override fun updateLesson(
        token: String,
        lessonId: Int,
        title: String,
        description: String,
        content: String
    ): Call<GeneralResponseModel> {
        return lessonAPIService.updateLesson(
            token,
            lessonId,
            LessonRequest(title, description, content)
        )
    }

    override fun deleteLesson(token: String, lessonId: Int): Call<GeneralResponseModel> {
        return lessonAPIService.deleteLesson(token, lessonId)
    }
}
