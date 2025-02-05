import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogsimage.presentation.mvvm.ImageViewModel
import com.example.dogsimage.presentation.mvvm.MyViewModel
import com.example.dogsimage.presentation.ui.imageScreen.imagePage.ImagePage
import com.example.dogsimage.presentation.ui.mainScreen.MainScreen

@Composable
fun ContentScreen(
    imageViewModel:ImageViewModel = viewModel(),
    myViewModel:MyViewModel  = viewModel()
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {


        composable("image_screen") {
            ImagePage(
                imageViewModel = imageViewModel,
                navController = navController
            )
        }
        composable("main_screen") {
            MainScreen(
                navController = navController,
                imageViewModel=imageViewModel,
                myViewModel = myViewModel
                )
        }
    }
}
