package com.example.alp_vp.uistates

import com.example.alp_vp.models.LessonModel

sealed interface LessonDataStatusUIState {
    data class Success(val data: List<LessonModel>) : LessonDataStatusUIState
    object Start : LessonDataStatusUIState
    object Loading : LessonDataStatusUIState
    data class Failed(val errorMessage: String) : LessonDataStatusUIState
}
