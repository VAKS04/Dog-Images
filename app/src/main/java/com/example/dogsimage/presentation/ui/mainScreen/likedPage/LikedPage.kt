package com.example.dogsimage.presentation.ui.mainScreen.likedPage

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dogsimage.presentation.mvvm.ImageViewModel
import com.example.dogsimage.presentation.mvvm.ImagesViewModel

@Composable
fun LikedPage (
    modifier: Modifier,
    viewModel:ImagesViewModel= hiltViewModel(),
    imageViewModel :ImageViewModel,
    navController: NavController
){
    val images by viewModel.allImages.collectAsState(emptyList())
    Log.d("Mag","{$images}")

    if (images.isNotEmpty()){
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier=modifier.fillMaxSize(),
        ) {
            items(images){image->
                Card(
                    data=image,
                    imageViewModel = imageViewModel,
                    navController = navController)
            }
        }
    }else{
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "You didn't like any of the image")
        }
    }


}