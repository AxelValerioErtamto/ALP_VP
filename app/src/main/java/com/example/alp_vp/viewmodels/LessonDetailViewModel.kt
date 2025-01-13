package com.example.alp_vp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.alp_vp.ParkhubApplication
import com.example.alp_vp.enums.PagesEnum
import com.example.alp_vp.models.ErrorModel
import com.example.alp_vp.models.LessonModel
import com.example.alp_vp.repositories.LessonRepository
import com.example.alp_vp.uistates.LessonDetailDataStatusUIState
import com.example.alp_vp.uistates.StringDataStatusUIState
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LessonDetailViewModel(
    private val lessonRepository: LessonRepository
) : ViewModel() {
    var dataStatus: LessonDetailDataStatusUIState by mutableStateOf(LessonDetailDataStatusUIState.Start)
        private set

    var deleteStatus: StringDataStatusUIState by mutableStateOf(StringDataStatusUIState.Start)
        private set

//    fun getLessonById(lessonId: Int, navController: NavHostController) {
//        viewModelScope.launch {
//            dataStatus = LessonDetailDataStatusUIState.Loading
//
//            try {
//                val call = lessonRepository.getLessonById(lessonId)
//
//                call.enqueue(object : Callback<LessonModel> {
//                    override fun onResponse(call: Call<LessonModel>, res: Response<LessonModel>) {
//                        if (res.isSuccessful) {
//                            dataStatus = LessonDetailDataStatusUIState.Success(res.body()!!)
//                            navController.navigate(PagesEnum.LessonDetail.name)
//                        } else {
//                            val errorMessage = Gson().fromJson(
//                                res.errorBody()!!.charStream(),
//                                ErrorModel::class.java
//                            )
//                            dataStatus = LessonDetailDataStatusUIState.Failed(errorMessage.errors)
//                        }
//                    }
//
//                    override fun onFailure(call: Call<LessonModel>, t: Throwable) {
//                        dataStatus = LessonDetailDataStatusUIState.Failed(t.localizedMessage)
//                    }
//                })
//            } catch (error: IOException) {
//                dataStatus = LessonDetailDataStatusUIState.Failed(error.localizedMessage)
//            }
//        }
//    }

//    fun deleteLesson(lessonId: Int, navController: NavHostController) {
//        viewModelScope.launch {
//            deleteStatus = StringDataStatusUIState.Loading
//
//            try {
//                val call = lessonRepository.deleteLesson(lessonId)
//
//                call.enqueue(object : Callback<Unit> {
//                    override fun onResponse(call: Call<Unit>, res: Response<Unit>) {
//                        if (res.isSuccessful) {
//                            deleteStatus = StringDataStatusUIState.Success("Lesson deleted successfully")
//                            navController.popBackStack()
//                        } else {
//                            val errorMessage = Gson().fromJson(
//                                res.errorBody()!!.charStream(),
//                                ErrorModel::class.java
//                            )
//                            deleteStatus = StringDataStatusUIState.Failed(errorMessage.errors)
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Unit>, t: Throwable) {
//                        deleteStatus = StringDataStatusUIState.Failed(t.localizedMessage)
//                    }
//                })
//            } catch (error: IOException) {
//                deleteStatus = StringDataStatusUIState.Failed(error.localizedMessage)
//            }
//        }
//    }

//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as ParkhubApplication)
//                val lessonRepository = application.container.lessonRepository
//                LessonDetailViewModel(lessonRepository)
//            }
//        }
//    }

    fun clearErrorMessage() {
        deleteStatus = StringDataStatusUIState.Start
    }
}
