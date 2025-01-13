//package com.example.alp_vp.viewmodels
//
//import android.util.Log
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.alp_vp.models.GeneralResponseModel
//import com.example.alp_vp.models.LessonModel
//import com.example.alp_vp.repositories.LessonRepository
//import com.example.alp_vp.uistates.LessonListFormUIState
//import com.example.alp_vp.uistates.StringDataStatusUIState
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.flow.asStateFlow
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.example.alp_vp.ParkhubApplication
//
//class LessonListFormViewModel(
//    private val lessonRepository: LessonRepository
//) : ViewModel() {
//
//    // State for Lesson List UI
//    private val _lessonListFormUIState = MutableStateFlow(LessonListFormUIState())
//    val lessonListFormUIState: StateFlow<LessonListFormUIState>
//        get() {
//            return _lessonListFormUIState.asStateFlow()
//        }
//
//    // Submission status (used to show success/failure loading state)
//    var submissionStatus: StringDataStatusUIState by mutableStateOf(StringDataStatusUIState.Start)
//        private set
//
//    // Fetch the list of lessons from the repository
//    fun fetchLessons() {
//        viewModelScope.launch {
//            submissionStatus = StringDataStatusUIState.Loading
//
////            lessonRepository.getAllLessons().enqueue(object : Callback<List<LessonModel>> {
////                override fun onResponse(call: Call<List<LessonModel>>, response: Response<List<LessonModel>>) {
////                    if (response.isSuccessful) {
////                        response.body()?.let { lessons ->
////                            // Update state with fetched lessons
////                            _lessonListFormUIState.value = LessonListFormUIState(lessonList = lessons)
////                            submissionStatus = StringDataStatusUIState.Success("Lessons fetched successfully")
////                        }
////                    } else {
////                        submissionStatus = StringDataStatusUIState.Failed("Failed to fetch lessons")
////                    }
////                }
////
////                override fun onFailure(call: Call<List<LessonModel>>, t: Throwable) {
////                    submissionStatus = StringDataStatusUIState.Failed(t.localizedMessage)
////                }
////            })
//        }
//    }
//
//    // Create a new lesson
////    fun createLesson(title: String, description: String, content: String, imageUri: String) {
////        viewModelScope.launch {
////            submissionStatus = StringDataStatusUIState.Loading
////
////            lessonRepository.createLesson(title, description, content, imageUri).enqueue(object : Callback<GeneralResponseModel> {
////                override fun onResponse(call: Call<GeneralResponseModel>, response: Response<GeneralResponseModel>) {
////                    if (response.isSuccessful) {
////                        submissionStatus = StringDataStatusUIState.Success("Lesson created successfully")
////                    } else {
////                        submissionStatus = StringDataStatusUIState.Failed("Failed to create lesson")
////                    }
////                }
////
////                override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
////                    submissionStatus = StringDataStatusUIState.Failed(t.localizedMessage)
////                }
////            })
////        }
////    }
//
//    // Reset the UI State (you can call this after a successful operation)
//    fun resetViewModel() {
//        submissionStatus = StringDataStatusUIState.Start
//        _lessonListFormUIState.value = LessonListFormUIState() // Reset the lesson list
//    }
//
//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as ParkhubApplication)
//                val userRepository = application.container.userRepository
//                val lessonRepository = application.container.userRepository
//                HomeViewModel(userRepository/*, todoRepository*/)
//            }
//        }
//    }
//
//}
