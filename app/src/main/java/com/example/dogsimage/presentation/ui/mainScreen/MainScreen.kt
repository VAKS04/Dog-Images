package com.example.dogsimage.presentation.ui.mainScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dogsimage.model.NavItemModel
import com.example.dogsimage.presentation.mvvm.ImageViewModel
import com.example.dogsimage.presentation.mvvm.MyViewModel
import com.example.dogsimage.presentation.ui.mainScreen.homePage.HomePage
import com.example.dogsimage.presentation.ui.mainScreen.likedPage.LikedPage

@Composable
fun MainScreen(
    navController: NavController,
    imageViewModel : ImageViewModel,
    myViewModel : MyViewModel
){

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val navItemList = listOf(
        NavItemModel(
            "Main",
            Icons.Default.Home
        ),
        NavItemModel(
            "Liked",
            Icons.Default.Favorite
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.clip(RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp)
                )
            ) {
                navItemList.forEachIndexed{ index,navItem ->
                    NavigationBarItem(
                        selected = selectedIndex==index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = navItem.image,
                                contentDescription = "Icon")

                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        ChoosePage(
            modifier = Modifier.padding(innerPadding),
            indexed = selectedIndex,
            navController = navController,
            imageViewModel = imageViewModel,
            myViewModel=myViewModel
        )
    }
}

@Composable
fun ChoosePage(
    modifier: Modifier,
    indexed:Int,
    navController: NavController,
    imageViewModel : ImageViewModel,
    myViewModel : MyViewModel
){


    when(indexed){
        0 -> HomePage(
            modifier = modifier,
            viewModel = myViewModel,
        )
        1 -> LikedPage(
            modifier = modifier,
            imageViewModel = imageViewModel,
            navController = navController
        )
    }
}