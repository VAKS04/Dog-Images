package com.example.dogsimage.presentation.ui.mainScreen.homePage


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.dogsimage.data.db.Images
import com.example.dogsimage.model.ApiModel
import com.example.dogsimage.presentation.mvvm.ImageViewModel
import com.example.dogsimage.presentation.mvvm.ImagesViewModel
import com.example.dogsimage.presentation.ui.theme.Dimensions

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    viewModel: ImagesViewModel = hiltViewModel(),
    card : ApiModel?,
){
    val context = LocalContext.current
    val imageLink = card?.message ?: "waiting..."

    var isFullscreen by remember {
        mutableStateOf(false)
    }

    Row(
        modifier=modifier
    ) {
        Box(
            modifier = modifier
                .height(Dimensions.cardHeight)
                .width(Dimensions.cardWidth)
                .padding(Dimensions.cardPadding)
                .clip(RoundedCornerShape(Dimensions.cornerRadius))
                .background(Color.Black)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            isFullscreen = !isFullscreen
                        },
                        onDoubleTap = {
                            viewModel.addImage(Images(link = imageLink))
                            Toast
                                .makeText(
                                    context, "Liked", Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    )
                },
            contentAlignment = Alignment.Center,

            ) {
            if (isFullscreen) {
                AsyncImage(
                    model = imageLink,
                    contentDescription = "Пример изображения",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }else{
                AsyncImage(
                    model = imageLink,
                    contentDescription = "Пример изображения",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
