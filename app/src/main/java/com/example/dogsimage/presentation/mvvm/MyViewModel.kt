package com.example.dogsimage.presentation.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogsimage.domain.ApiRepository
import com.example.dogsimage.model.ApiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyViewModel:ViewModel() {
    private val _listImageFlow = MutableStateFlow<MutableList<ApiModel?>>(mutableListOf())
    val listImageFlow : StateFlow<List<ApiModel?>> get() = _listImageFlow

    fun getResponse(){
        viewModelScope.launch {
            getCountRequest(30)
        }
    }

    private suspend fun getCountRequest(count: Int){
        var x = 0

        while (x < count){
            _listImageFlow.update { values ->
                (values + ApiRepository.getRandomImage()).toMutableList()
            }
            x++
        }
    }
}