package com.example.dogsimage.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiModel(
    val message:String,
    val status: String
)