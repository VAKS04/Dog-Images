package com.example.dogsimage.data.api

import com.example.dogsimage.model.ApiModel
import retrofit2.http.GET

interface ApiService {
    @GET("breeds/image/random")
    suspend fun getRandomLink():ApiModel
}