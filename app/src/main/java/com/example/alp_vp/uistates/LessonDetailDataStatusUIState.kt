package com.example.alp_vp.uistates

import com.example.alp_vp.models.LessonModel

sealed interface LessonDetailDataStatusUIState {
    data class Success(val data: LessonModel) : LessonDetailDataStatusUIState
    object Loading : LessonDetailDataStatusUIState
    object Start : LessonDetailDataStatusUIState
    data class Failed(val errorMessage: String) : LessonDetailDataStatusUIState
}
