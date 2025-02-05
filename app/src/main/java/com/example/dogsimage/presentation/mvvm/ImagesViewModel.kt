package com.example.dogsimage.presentation.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dogsimage.data.db.Images
import com.example.dogsimage.domain.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val repository: ImagesRepository
):ViewModel() {
    val allImages: StateFlow<List<Images?>> = repository.allImages

    fun addImage(images: Images){
        viewModelScope.launch {
            repository.insertImages(images)
        }
    }
    fun deleteImage(images: Images){
        viewModelScope.launch {
            repository.deleteImages(images)
        }
    }
}