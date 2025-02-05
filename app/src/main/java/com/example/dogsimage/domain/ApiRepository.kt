package com.example.dogsimage.domain

import android.util.Log
import com.example.dogsimage.data.api.ApiService
import com.example.dogsimage.domain.ApiRepository.Companion.BASE_URL
import com.example.dogsimage.model.ApiModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ApiRepository {
    companion object {
        private const val BASE_URL = "https://dog.ceo/api/"

        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        private val contentType = "application/json".toMediaType()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(client)
            .build()

        private val service = retrofit.create(ApiService::class.java)

        suspend fun getRandomImage(): ApiModel? {
            return try {
                service.getRandomLink() // Используем suspend версию API
            } catch (e: Exception) {
                Log.e("ApiRepository", "Error fetching image", e)
                null
            }
        }
    }
}
