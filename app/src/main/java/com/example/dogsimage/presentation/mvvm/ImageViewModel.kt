package com.example.dogsimage.presentation.mvvm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsimage.data.db.Images
import kotlinx.coroutines.launch

class ImageViewModel:ViewModel() {
    private val _imageData = mutableStateOf<String?>(null)
    val imageData : State<String?> = _imageData

    fun writeData(image: String){
        viewModelScope.launch {
            _imageData.value = image
        }
    }
}