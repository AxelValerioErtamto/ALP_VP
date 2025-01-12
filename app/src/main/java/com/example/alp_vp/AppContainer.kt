package com.example.alp_vp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.alp_vp.repositories.AuthenticationRepository
import com.example.alp_vp.repositories.NetworkAuthenticationRepository
import com.example.alp_vp.repositories.NetworkReportRepository
// import com.example.alp_vp.repositories.NetworkTodoRepository
import com.example.alp_vp.repositories.NetworkUserRepository
import com.example.alp_vp.repositories.ReportRepository
// import com.example.alp_vp.repositories.TodoRepository
import com.example.alp_vp.repositories.UserRepository
import com.example.alp_vp.services.AuthenticationAPIService
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
    val reportRepository: ReportRepository
    // val todoRepository: TodoRepository
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

    private val reportRetrofitService: ReportAPIService by lazy {
        retrofit.create(ReportAPIService::class.java)
    }

    // private val todoAPIService: TodoAPIService by lazy {
    //     retrofit.create(TodoAPIService::class.java)
    // }

    override val authenticationRepository: AuthenticationRepository by lazy {
        NetworkAuthenticationRepository(authenticationAPIService)
    }

    override val userRepository: UserRepository by lazy {
        NetworkUserRepository(userDataStore, userAPIService)
    }

    override val reportRepository: ReportRepository by lazy {
        NetworkReportRepository(reportRetrofitService)
    }

    // override val todoRepository: TodoRepository by lazy {
    //     NetworkTodoRepository(todoAPIService)
    // }

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
