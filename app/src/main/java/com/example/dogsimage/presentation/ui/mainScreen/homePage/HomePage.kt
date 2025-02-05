package com.example.dogsimage.presentation.ui.mainScreen.homePage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dogsimage.presentation.mvvm.ImageViewModel
import com.example.dogsimage.presentation.mvvm.MyViewModel

@Composable
fun HomePage(
    modifier: Modifier,
    viewModel: MyViewModel,
) {
    val listImage by viewModel.listImageFlow.collectAsState(emptyList())
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { //создание Flow для отслеживания изменения
            listState.firstVisibleItemIndex}
            .collect{index ->
                if (index % 10 == 0){
                    viewModel.getResponse()
                }
            }
        }

    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        items(listImage){card->
            ImageCard(card = card)
        }
    }
}
