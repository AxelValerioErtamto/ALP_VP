package com.example.alp_vp.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.alp_vp.ParkhubApplication
import com.example.alp_vp.enums.PagesEnum
import com.example.alp_vp.models.ErrorModel
import com.example.alp_vp.models.UserResponse
import com.example.alp_vp.repositories.AuthenticationRepository
import com.example.alp_vp.repositories.UserRepository
import com.example.alp_vp.uistates.AuthenticationStatusUIState
import com.example.alp_vp.uistates.AuthenticationUIState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class AuthenticationViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _authenticationUIState = MutableStateFlow(AuthenticationUIState())
    val authenticationUIState: StateFlow<AuthenticationUIState> = _authenticationUIState.asStateFlow()

    var dataStatus: AuthenticationStatusUIState by mutableStateOf(AuthenticationStatusUIState.Start)
        private set

    var emailInput by mutableStateOf("")
        private set
    var passwordInput by mutableStateOf("")
        private set
    var confirmPasswordInput by mutableStateOf("")
        private set
    var usernameInput by mutableStateOf("")
        private set

    fun updateEmailInput(value: String) {
        emailInput = value
        checkLoginForm()
    }

    fun updatePasswordInput(value: String) {
        passwordInput = value
        checkLoginForm()
    }

    fun updateUsernameInput(value: String) {
        usernameInput = value
        checkRegisterForm()
    }

    fun updateConfirmPasswordInput(value: String) {
        confirmPasswordInput = value
        checkRegisterForm()
    }

    fun togglePasswordVisibility() {
        _authenticationUIState.update { state ->
            state.copy(
                showPassword = !state.showPassword,
                passwordVisibility = if (state.showPassword) PasswordVisualTransformation() else VisualTransformation.None
            )
        }
    }

    fun toggleConfirmPasswordVisibility() {
        _authenticationUIState.update { state ->
            state.copy(
                showConfirmPassword = !state.showConfirmPassword,
                confirmPasswordVisibility = if (state.showConfirmPassword) PasswordVisualTransformation() else VisualTransformation.None
            )
        }
    }

    private fun checkLoginForm() {
        _authenticationUIState.update {
            it.copy(buttonEnabled = emailInput.isNotEmpty() && passwordInput.isNotEmpty())
        }
    }

    private fun checkRegisterForm() {
        _authenticationUIState.update {
            it.copy(
                buttonEnabled = usernameInput.isNotEmpty() &&
                        emailInput.isNotEmpty() &&
                        passwordInput.isNotEmpty() &&
                        confirmPasswordInput.isNotEmpty() &&
                        passwordInput == confirmPasswordInput
            )
        }
    }

    fun registerUser(onSuccess: (PagesEnum) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            dataStatus = AuthenticationStatusUIState.Loading
            try {
                val call = authenticationRepository.register(usernameInput, emailInput, passwordInput)
                call.enqueue(object : Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        if (response.isSuccessful) {
                            val user = response.body()?.data
                            if (user != null) {
                                saveUser(user.token ?: "", user.username)
                                resetState()
                                onSuccess(PagesEnum.Home)
                            }
                        } else {
                            val errorMessage = Gson().fromJson(response.errorBody()?.charStream(), ErrorModel::class.java)
                            onError(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        onError(t.message ?: "Unknown error occurred")
                    }
                })
            } catch (e: IOException) {
                onError(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun loginUser(onSuccess: (PagesEnum) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            dataStatus = AuthenticationStatusUIState.Loading
            try {
                val call = authenticationRepository.login(emailInput, passwordInput)
                call.enqueue(object : Callback<UserResponse> {
                    override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                        if (response.isSuccessful) {
                            val user = response.body()?.data
                            if (user != null) {
                                saveUser(user.token ?: "", user.username)
                                resetState()
                                onSuccess(PagesEnum.Home)
                            }
                        } else {
                            val errorMessage = Gson().fromJson(response.errorBody()?.charStream(), ErrorModel::class.java)
                            onError(errorMessage.errors)
                        }
                    }

                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        onError(t.message ?: "Unknown error occurred")
                    }
                })
            } catch (e: IOException) {
                onError(e.message ?: "Unknown error occurred")
            }
        }
    }

    private fun saveUser(token: String, username: String) {
        viewModelScope.launch {
            userRepository.saveUserToken(token)
            userRepository.saveUsername(username)
        }
    }

    fun resetState() {
        emailInput = ""
        passwordInput = ""
        confirmPasswordInput = ""
        usernameInput = ""
        _authenticationUIState.update {
            it.copy(buttonEnabled = false, showPassword = false, showConfirmPassword = false)
        }
        dataStatus = AuthenticationStatusUIState.Start
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as ParkhubApplication
                val authenticationRepository = application.container.authenticationRepository
                val userRepository = application.container.userRepository
                AuthenticationViewModel(authenticationRepository, userRepository)
            }
        }
    }
}
