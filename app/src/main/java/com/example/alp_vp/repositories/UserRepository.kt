
package com.example.alp_vp.repositories


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.alp_vp.models.GeneralResponseModel
import com.example.alp_vp.services.UserAPIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import okio.Timeout
import retrofit2.Call

interface UserRepository {
    val currentUserToken: Flow<String>
    val currentUsername: Flow<String>
    val currentUserId: Flow<Int>

    fun logout(token: String): Call<GeneralResponseModel>

    suspend fun saveUserToken(token: String)

    suspend fun saveUsername(username: String)

    suspend fun saveUserId(id: Int)
}

class NetworkUserRepository(
    private val userDataStore: DataStore<Preferences>,
    private val userAPIService: UserAPIService
): UserRepository {
    private companion object {
        val USER_TOKEN = stringPreferencesKey("token")
        val USERNAME = stringPreferencesKey("username")
        val USER_ID = stringPreferencesKey("id")
    }

    override val currentUserToken: Flow<String> = userDataStore.data.map { preferences ->
        preferences[USER_TOKEN] ?: "Unknown"
    }

    override val currentUsername: Flow<String> = userDataStore.data.map { preferences ->
        preferences[USERNAME] ?: "Unknown"
    }

    override val currentUserId: Flow<Int> = userDataStore.data.map { preferences ->
        preferences[USER_ID]?.toIntOrNull() ?: 0
    }

    override suspend fun saveUserToken(token: String) {
        userDataStore.edit { preferences ->
            preferences[USER_TOKEN] = token
        }
    }

    override suspend fun saveUsername(username: String) {
        userDataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    override suspend fun saveUserId(id: Int) {
        userDataStore.edit { preferences ->
            preferences[USER_ID] = id.toString()
        }
    }

    override fun logout(token: String): Call<GeneralResponseModel> {
        return userAPIService.logout(token)
    }
}

class MockUserRepository : UserRepository {
    private var mockUserToken: String = "mockToken"
    private var mockUsername: String = "mockUsername"
    private var mockUserId: Int = 1 // Default mock user ID

    // Mocking currentUserToken as a Flow
    override val currentUserToken: Flow<String> = flow {
        emit(mockUserToken) // Simulate emitting a mock user token
    }

    // Mocking currentUsername as a Flow
    override val currentUsername: Flow<String> = flow {
        emit(mockUsername) // Simulate emitting a mock username
    }

    // Mocking currentUserId as a Flow
    override val currentUserId: Flow<Int> = flow {
        emit(mockUserId) // Simulate emitting a mock user ID
    }

    // Mocking saveUserToken (no-op)
    override suspend fun saveUserToken(token: String) {
        mockUserToken = token // Simulate saving the user token
    }

    // Mocking saveUsername (no-op)
    override suspend fun saveUsername(username: String) {
        mockUsername = username // Simulate saving the username
    }

    // Mocking saveUserId (no-op)
    override suspend fun saveUserId(id: Int) {
        mockUserId = id // Simulate saving the user ID
    }

    // Mocking the logout function, assuming a successful logout with a mock response
    override fun logout(token: String): Call<GeneralResponseModel> {
        // Mock response for successful logout
        val mockResponse = GeneralResponseModel("Logged out successfully")
        return object : Call<GeneralResponseModel> {
            override fun enqueue(callback: retrofit2.Callback<GeneralResponseModel>) {
                callback.onResponse(this, retrofit2.Response.success(mockResponse)) // Simulate success
            }

            override fun isExecuted(): Boolean = false
            override fun clone(): Call<GeneralResponseModel> = this
            override fun execute(): retrofit2.Response<GeneralResponseModel> {
                return retrofit2.Response.success(mockResponse)
            }

            override fun cancel() {}
            override fun isCanceled(): Boolean = false
            override fun request(): okhttp3.Request = okhttp3.Request.Builder().url("http://example.com").build()

            override fun timeout(): Timeout {
                return Timeout.NONE // Returning a default timeout
            }
        }
    }
}
