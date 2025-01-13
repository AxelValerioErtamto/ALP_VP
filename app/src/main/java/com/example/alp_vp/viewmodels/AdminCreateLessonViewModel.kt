package com.example.alp_vp.viewmodels

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
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AdminCreateLessonViewModel(
    private val lessonRepository: LessonRepository // Injecting the repository
) : ViewModel() {

    // Mutable states for each input field
    private val _title = mutableStateOf("")
    val title: State<String> get() = _title

    private val _description = mutableStateOf("")
    val description: State<String> get() = _description

    private val _content = mutableStateOf("")
    val content: State<String> get() = _content

    private val _isLoading = mutableStateOf(false) // Track loading state
    val isLoading: State<Boolean> get() = _isLoading

    private val _creationError = mutableStateOf<String?>(null) // Track errors
    val creationError: State<String?> get() = _creationError

    // Function to update state variables
    fun updateTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun updateDescription(newDescription: String) {
        _description.value = newDescription
    }

    fun updateContent(newContent: String) {
        _content.value = newContent
    }

    // Function to handle lesson creation
    fun createLesson(token: String) {
        // Validation check
        if (_title.value.isBlank() || _description.value.isBlank() || _content.value.isBlank()) {
            _creationError.value = "Please fill in all fields"
            return
        }

        _isLoading.value = true // Start loading

        // Call the repository to create a lesson
        viewModelScope.launch {

            lessonRepository.createLesson(
                token,
                _title.value,
                _description.value,
                _content.value,
            ).enqueue(object : Callback<GeneralResponseModel> {
                override fun onResponse(
                    call: Call<GeneralResponseModel>,
                    response: Response<GeneralResponseModel>
                ) {
                    _isLoading.value = false // Stop loading

                    if (response.isSuccessful) {
                        // Handle success (e.g., navigate back or show success message)
                    } else {
                        _creationError.value = response.message() // Handle API failure
                    }
                }

                override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                    _isLoading.value = false // Stop loading
                    _creationError.value = t.message // Handle network failure
                }
            })
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Use the application context to access repositories via the app's container
                val application = (this[APPLICATION_KEY] as ParkhubApplication)
                val lessonRepository = application.container.lessonRepository // Assuming the repository is part of the DI container
                AdminCreateLessonViewModel(lessonRepository)
            }
        }
    }
}
