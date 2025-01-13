package com.example.alp_vp.models

import com.google.gson.annotations.SerializedName

data class GetLessonResponse(
    val data: LessonModel
)


//Response
data class LessonModel(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val content: String = "",
)

//Request
data class LessonRequest(
    val title: String = "",
    val description: String = "",
    val content: String = "",
)

data class UpdateLessonRequest(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val content: String? = null
)
