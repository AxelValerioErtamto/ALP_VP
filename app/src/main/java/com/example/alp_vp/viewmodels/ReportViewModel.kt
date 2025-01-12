package com.example.alp_vp.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.alp_vp.ParkhubApplication
import com.example.alp_vp.repositories.ReportRepository
import com.example.alp_vp.repositories.UserRepository
import com.example.alp_vp.uistates.ReportStatusUIState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.alp_vp.enums.PagesEnum
import com.example.alp_vp.models.ErrorModel
import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.GetAllReportResponse
import com.example.alp_vp.models.ReportModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReportViewModel (
    private val reportRepository: ReportRepository,
    private val userRepository: UserRepository
): ViewModel(){

    val username: StateFlow<String> = userRepository.currentUsername.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val token: StateFlow<String> = userRepository.currentUserToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    var uiState: ReportStatusUIState by mutableStateOf(ReportStatusUIState.Start)
        private set


    var reportId: Int by mutableStateOf(0)
        private set

    var report_title_input: String by mutableStateOf("")
        private set

    var report_description_input: String by mutableStateOf("")
        private set

    //var report_image_input: String by mutableStateOf("")


    fun setTitle(title:String) {
        report_title_input = title
    }

    fun setDescription(description:String){
        report_description_input = description
    }

//    fun setImage(image:String){
//        report_image_input = image
//    }

    fun resetViewModel(){
        setTitle("")
        setDescription("")
       // setImage("")
    }


    private val _reportById = MutableStateFlow<List<ReportModel>>(emptyList())
    val reportById: StateFlow<List<ReportModel>> = _reportById


    fun createReport(token: String, id: Int, navController: NavHostController) {
        uiState = ReportStatusUIState.Loading
        viewModelScope.launch {
            try {
                val response = reportRepository.createReport(
                    token,
                    userId = id,
                    title = report_title_input,
                    description = report_description_input,
                    image = ""
                )

                response.enqueue(object : Callback<GeneralResponseModel> {
                    override fun onResponse(
                        call: Call<GeneralResponseModel>,
                        res: Response<GeneralResponseModel>
                    ) {
                        if(res.isSuccessful){
                            uiState = ReportStatusUIState.Success(res.body()!!.data)
                            resetViewModel()
                            navController.navigate(PagesEnum.ReportPage.name) {
                                popUpTo(PagesEnum.CreateReport.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                ErrorModel::class.java
                            )

                            Log.d("error-data", "Error Data: $errorMessage")
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t:  Throwable) {
                        Log.d("error-data", "Error Data: ${t.localizedMessage}")
                    }

                })
            } catch (e:Exception) {
                Log.d("error-data", "Error Data: ${e.localizedMessage}")
            }
        }
    }

    fun getAllReports(token: String){
        viewModelScope.launch {
            try {
                val response = reportRepository.getAllReports(token)

                response.enqueue(object: Callback<GetAllReportResponse>{
                    override fun onResponse(
                        call: Call<GetAllReportResponse>,
                        res: Response<GetAllReportResponse>
                    ) {
                        if(res.isSuccessful){
                            val reportById = res.body()!!.data
                            _reportById.value = reportById
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()?.charStream(),
                                ErrorModel::class.java
                            )
                            Log.d("getReport-error", "Error Data: ${errorMessage}")
                        }
                    }

                    override fun onFailure(p0: Call<GetAllReportResponse>, t: Throwable) {
                        Log.d("error-data", "Error Data: ${t.localizedMessage}")
                    }
                })
            } catch (e: Exception){
                Log.d("error-data", "Error Data: ${e.localizedMessage}")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ParkhubApplication)
                val reportRepository = application.container.reportRepository
                val userRepository = application.container.userRepository
                ReportViewModel(reportRepository, userRepository)

            }
        }
    }



}