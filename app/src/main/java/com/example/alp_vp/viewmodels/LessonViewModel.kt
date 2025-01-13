package com.example.alp_vp.viewmodels.lesson

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.alp_vp.ParkhubApplication
import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.LessonModel
import com.example.alp_vp.repositories.LessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class LessonViewModel(private val repository: LessonRepository) : ViewModel() {

    val lessons = mutableStateOf<List<LessonModel>>(emptyList())
    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    // Fetch all lessons
    fun fetchAllLessons(token: String) {
        isLoading.value = true
        errorMessage.value = null

        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllLessons(token).enqueue(object : Callback<List<LessonModel>> {
                override fun onResponse(
                    call: Call<List<LessonModel>>,
                    response: Response<List<LessonModel>>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        lessons.value = response.body() ?: emptyList()
                    } else {
                        errorMessage.value = "Failed to fetch lessons: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<List<LessonModel>>, t: Throwable) {
                    isLoading.value = false
                    errorMessage.value = "Failed to fetch lessons: ${t.message}"
                }
            })
        }
    }

    // Get a single lesson by ID
    fun getLessonById(lessonId: Int): LessonModel? {
        return lessons.value.find { it.id == lessonId }
    }

    // Update lesson
    fun updateLesson(
        token: String,
        lessonId: Int,
        title: String,
        description: String,
        content: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        isLoading.value = true
        repository.updateLesson(token, lessonId, title, description, content)
            .enqueue(object : Callback<GeneralResponseModel> {
                override fun onResponse(
                    call: Call<GeneralResponseModel>,
                    response: Response<GeneralResponseModel>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        onSuccess()
                    } else {
                        onError("Failed to update lesson: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                    isLoading.value = false
                    onError("Failed to update lesson: ${t.message}")
                }
            })
    }

    // Delete lesson
    fun deleteLesson(
        token: String,
        lessonId: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        isLoading.value = true
        repository.deleteLesson(token, lessonId)
            .enqueue(object : Callback<GeneralResponseModel> {
                override fun onResponse(
                    call: Call<GeneralResponseModel>,
                    response: Response<GeneralResponseModel>
                ) {
                    isLoading.value = false
                    if (response.isSuccessful) {
                        lessons.value = lessons.value.filter { it.id != lessonId }
                        onSuccess()
                    } else {
                        onError("Failed to delete lesson: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                    isLoading.value = false
                    onError("Failed to delete lesson: ${t.message}")
                }
            })
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Use the application context to access repositories via the app's container
                val application = (this[APPLICATION_KEY] as ParkhubApplication)
                val lessonRepository = application.container.lessonRepository // Assuming the repository is part of the DI container
                LessonViewModel(lessonRepository)
            }
        }
    }
}
