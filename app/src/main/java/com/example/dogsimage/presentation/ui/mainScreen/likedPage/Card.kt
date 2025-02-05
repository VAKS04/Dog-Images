package com.example.dogsimage.presentation.ui.mainScreen.likedPage

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.dogsimage.data.db.Images
import com.example.dogsimage.presentation.mvvm.ImageViewModel
import com.example.dogsimage.presentation.mvvm.ImagesViewModel

@Composable
fun Card(
    modifier: Modifier =Modifier,
    data: Images?,
    imageViewModel: ImageViewModel,
    viewModel: ImagesViewModel = hiltViewModel(),
    navController: NavController
){

    val notNullData:Images
    if (data == null){
        throw Error()
    }else{
        notNullData = data
    }
    val context = LocalContext.current
    Box(
        modifier = modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        imageViewModel.writeData(data.link)
                        navController.navigate("image_screen")
                    },
                    onDoubleTap = {
                        viewModel.deleteImage(notNullData)
                        Toast
                            .makeText(context, "delete", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            },
        contentAlignment = Alignment.Center
    ){
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            model = data.link,
            contentDescription ="Пример изображения",
            contentScale = ContentScale.Crop
        )
    }
}