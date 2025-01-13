package com.example.alp_vp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.alp_vp.repositories.AuthenticationRepository
import com.example.alp_vp.repositories.LessonRepository
import com.example.alp_vp.repositories.LocationRepository
import com.example.alp_vp.repositories.NetworkAuthenticationRepository
import com.example.alp_vp.repositories.NetworkLessonRepository
import com.example.alp_vp.repositories.NetworkLocationRepository
import com.example.alp_vp.repositories.NetworkReportRepository
// import com.example.alp_vp.repositories.NetworkTodoRepository
import com.example.alp_vp.repositories.NetworkUserRepository
import com.example.alp_vp.repositories.ReportRepository
// import com.example.alp_vp.repositories.TodoRepository
import com.example.alp_vp.repositories.UserRepository
import com.example.alp_vp.services.AuthenticationAPIService
import com.example.alp_vp.services.LessonAPIService
import com.example.alp_vp.services.LocationAPIService
import com.example.alp_vp.services.ReportAPIService
// import com.example.alp_vp.services.TodoAPIService
import com.example.alp_vp.services.UserAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val authenticationRepository: AuthenticationRepository
    val userRepository: UserRepository
    val locationRepository: LocationRepository
    val lessonRepository: LessonRepository
    val reportRepository: ReportRepository
}

class DefaultAppContainer(
    private val userDataStore: DataStore<Preferences>
) : AppContainer {
//    private val baseUrl = "http://192.168.18.252:3000/"
//    private val baseUrl = "http://192.168.232.233:3000/"
    private val baseUrl = "http://192.168.56.1:3000"
    private val retrofit: Retrofit by lazy { createRetrofit() }

    private val authenticationAPIService: AuthenticationAPIService by lazy {
        retrofit.create(AuthenticationAPIService::class.java)
    }

    private val userAPIService: UserAPIService by lazy {
        retrofit.create(UserAPIService::class.java)
    }

    private val locationAPIService: LocationAPIService by lazy {
        retrofit.create(LocationAPIService::class.java)
    }

    private val reportRetrofitService: ReportAPIService by lazy {
        retrofit.create(ReportAPIService::class.java)
    }

    private val lessonAPIService: LessonAPIService by lazy {
        retrofit.create(LessonAPIService::class.java)
    }

    override val authenticationRepository: AuthenticationRepository by lazy {
        NetworkAuthenticationRepository(authenticationAPIService)
    }

    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(userDataStore, userAPIService)
    }

    override val locationRepository: LocationRepository by lazy {
        NetworkLocationRepository(locationAPIService)
    }

    override val reportRepository: ReportRepository by lazy {
        NetworkReportRepository(reportRetrofitService)
    }
    override val lessonRepository: LessonRepository by lazy {
        NetworkLessonRepository(lessonAPIService)
    }

    private fun createRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
