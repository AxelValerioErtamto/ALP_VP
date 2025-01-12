package com.example.alp_vp.models

import com.google.gson.annotations.SerializedName

data class GetAllLessonResponse(
    val data: List<LessonModel>
)

data class GetLessonResponse(
    val data: LessonModel
)

data class UploadImageResponse(
    val message: String,
    val fileUrl: String
)

//Response
data class LessonModel(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val content: String = "",

    @SerializedName("image_uri")
    val imageUri: String = ""
)

//Request
data class LessonRequest(
    val title: String = "",
    val description: String = "",
    val content: String = "",

    @SerializedName("image_uri")
    val imageUri: String = ""
)
