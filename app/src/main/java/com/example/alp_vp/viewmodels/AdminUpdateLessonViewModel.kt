package com.example.alp_vp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.alp_vp.ParkhubApplication
import com.example.alp_vp.repositories.LessonRepository
import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.GetLessonResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class AdminUpdateLessonViewModel(
    private val lessonRepository: LessonRepository // Injecting the repository
) : ViewModel() {

    // Mutable states for each input field
    private val _lessonId = mutableStateOf(0) // Track the lesson ID
    val lessonId: State<Int> get() = _lessonId

    private val _title = mutableStateOf("")
    val title: State<String> get() = _title

    private val _description = mutableStateOf("")
    val description: State<String> get() = _description

    private val _content = mutableStateOf("")
    val content: State<String> get() = _content

    private val _isLoading = mutableStateOf(false) // Track loading state
    val isLoading: State<Boolean> get() = _isLoading

    private val _updateError = mutableStateOf<String?>(null) // Track errors
    val updateError: State<String?> get() = _updateError

    // Function to update state variables
    fun updateLessonId(id: Int) {
        _lessonId.value = id
    }

    fun updateTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun updateDescription(newDescription: String) {
        _description.value = newDescription
    }

    fun updateContent(newContent: String) {
        _content.value = newContent
    }

    // Function to fetch lesson details
    fun fetchLessonDetails(token: String, id: Int) {
        _updateError.value = null

        lessonRepository.getLesson(token, id).enqueue(object : Callback<GetLessonResponse> {
            override fun onResponse(call: Call<GetLessonResponse>, response: Response<GetLessonResponse>) {
                if (response.isSuccessful) {
                    val lesson = response.body()?.data // Assuming GetLessonResponse has a 'data' field
                    if (lesson != null) {
                        _lessonId.value = lesson.id
                        _title.value = lesson.title
                        _description.value = lesson.description
                        _content.value = lesson.content
                    } else {
                        _updateError.value = "Lesson details not found"
                    }
                } else {
                    _updateError.value = "Failed to fetch lesson details: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<GetLessonResponse>, t: Throwable) {
                _updateError.value = "Network error: ${t.message}"
            }
        })
    }


    // Function to handle lesson update
    fun updateLesson(token: String) {
        if (_lessonId.value <= 0 || _title.value.isBlank() || _description.value.isBlank() || _content.value.isBlank()) {
            _updateError.value = "Please fill in all fields"
            return
        }

        _isLoading.value = true // Set loading to true

        // Use viewModelScope to launch the coroutine
        viewModelScope.launch {
            try {
                val response = lessonRepository.updateLesson(
                    token,
                    _lessonId.value,
                    _title.value,
                    _description.value,
                    _content.value
                )
            } catch (e: Exception) {
                // Handle network or unexpected errors
                _updateError.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false // Reset loading state
            }
        }
    }



//
//    fun updateLesson(
//        token: String,
//        lessonId: Int,
//        title: String,
//        description: String,
//        content: String,
//        onSuccess: () -> Unit,
//        onError: (String) -> Unit
//    ) {
//        _isLoading.value = true
//
//        lessonRepository.updateLesson(token, lessonId, title, description, content)
//            .enqueue(object : Callback<GeneralResponseModel> {
//                override fun onResponse(
//                    call: Call<GeneralResponseModel>,
//                    response: Response<GeneralResponseModel>
//                ) {
//                    _isLoading.value = false
//                    if (response.isSuccessful) {
//                        onSuccess()
//                    } else {
//                        onError("Failed to update lesson: ${response.message()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
//                    _isLoading.value = false
//                    onError("Failed to update lesson: ${t.message}")
//                }
//            })
//    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Use the application context to access repositories via the app's container
                val application = (this[APPLICATION_KEY] as ParkhubApplication)
                val lessonRepository = application.container.lessonRepository // Assuming the repository is part of the DI container
                AdminUpdateLessonViewModel(lessonRepository)
            }
        }
    }
}
