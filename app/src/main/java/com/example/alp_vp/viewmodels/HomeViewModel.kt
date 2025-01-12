package com.example.alp_vp.viewmodels

// import com.example.alp_vp.models.GetAllTodoResponse
// import com.example.alp_vp.repositories.TodoRepository
// import com.example.alp_vp.uiStates.AuthenticationUIState
// import com.example.alp_vp.uiStates.HomeUIState
// import com.example.alp_vp.uiStates.TodoDataStatusUIState
import android.util.Log
import androidx.compose.runtime.State
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
import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.models.LocationModel
import com.example.alp_vp.repositories.LocationRepository
import com.example.alp_vp.repositories.UserRepository
import com.example.alp_vp.uistates.StringDataStatusUIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeViewModel(
    private val userRepository: UserRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    var logoutStatus: StringDataStatusUIState by mutableStateOf(StringDataStatusUIState.Start)
        private set

    val token: StateFlow<String> = userRepository.currentUserToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    private val _gedungSlots = mutableStateOf(0)
    val gedungSlots: State<Int> get() = _gedungSlots

    private val _bukitSlots = mutableStateOf(0)
    val bukitSlots: State<Int> get() = _bukitSlots

    private val _lapanganSlots = mutableStateOf(0)
    val lapanganSlots: State<Int> get() = _lapanganSlots

    init {
        loadLocations()
    }

    private fun loadLocations() {
        // Use the token from the currentUserToken flow in userRepository
        viewModelScope.launch {
            userRepository.currentUserToken.collect { token ->
                if (token.isNotEmpty()) {
                    // Now you can safely use the token to call the repository method
                    locationRepository.getAllLocations(token, onResult = { locations ->
                        // Filter the locations based on type
                        val gedungLocations =
                            locations?.filter { it.nama.startsWith("Gedung") } ?: emptyList()
                        val bukitLocations =
                            locations?.filter { it.nama.startsWith("Bukit") } ?: emptyList()
                        val lapanganLocations =
                            locations?.filter { it.nama.startsWith("Lapangan") } ?: emptyList()

                        // Calculate the available slots for each
                        _gedungSlots.value = calculateAvailableSlots(gedungLocations)
                        _bukitSlots.value = calculateAvailableSlots(bukitLocations)
                        _lapanganSlots.value = calculateAvailableSlots(lapanganLocations)

                    }, onError = { error ->
                        // Handle error
                        Log.e("HomeViewModel", "Error loading locations: $error")
                    })
                } else {
                    // Handle the case where the token is empty or not available
                    Log.e("HomeViewModel", "Token is empty or unavailable.")
                }
            }
        }
    }


    // Function to calculate available slots based on isFilled
    private fun calculateAvailableSlots(locations: List<LocationModel>): Int {
        val totalSpots = locations.size
        val filledSpots = locations.count { it.isFilled }
        return totalSpots - filledSpots // Available slots are the total minus filled spots
    }

    fun logoutUser(token: String, navController: NavHostController) {
        viewModelScope.launch {
            logoutStatus = StringDataStatusUIState.Loading

            Log.d("token-logout", "LOGOUT TOKEN: ${token}")

            try {
                val call = userRepository.logout(token)

                call.enqueue(object : Callback<GeneralResponseModel> {
                    override fun onResponse(
                        call: Call<GeneralResponseModel>,
                        res: Response<GeneralResponseModel>
                    ) {
                        if (res.isSuccessful) {
                            logoutStatus = StringDataStatusUIState.Success(data = res.body()!!.data)

                            saveUsernameToken("Unknown", "Unknown")

                            navController.navigate(PagesEnum.Login.name) {
                                popUpTo(PagesEnum.Home.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            val errorMessage = Gson().fromJson(
                                res.errorBody()!!.charStream(),
                                ErrorModel::class.java
                            )

                            logoutStatus = StringDataStatusUIState.Failed(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<GeneralResponseModel>, t: Throwable) {
                        logoutStatus = StringDataStatusUIState.Failed(t.localizedMessage)
                        Log.d("logout-failure", t.localizedMessage)
                    }
                })
            } catch (error: IOException) {
                logoutStatus = StringDataStatusUIState.Failed(error.localizedMessage)
                Log.d("logout-error", error.localizedMessage)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ParkhubApplication)
                val userRepository = application.container.userRepository
                val locationRepository = application.container.locationRepository
                HomeViewModel(userRepository, locationRepository)
            }
        }
    }

    fun saveUsernameToken(token: String, username: String) {
        viewModelScope.launch {
            userRepository.saveUserToken(token)
            userRepository.saveUsername(username)
        }
    }

}