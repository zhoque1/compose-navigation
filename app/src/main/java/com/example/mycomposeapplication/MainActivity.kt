package com.example.mycomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycomposeapplication.ui.navigation.NavComposeApp
import com.example.mycomposeapplication.ui.navigation.Navigation
import com.example.mycomposeapplication.ui.screens.demo.GalleryViewModel
import org.koin.androidx.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavComposeApp()
//            Surface(
//                modifier = Modifier.fillMaxSize(),
//            ) {
//                //GalleryList(viewModel = galleryViewModel, context = this)
//                DemoAppNavigation()
//            }
        }
    }
}




//@Composable
//fun DemoAppNavigation() {
//    val navController = rememberNavController()
//    NavHost(navController, startDestination = Navigation.Route.GALLERY_LIST) {
//        composable(route = Navigation.Route.GALLERY_LIST) {
//            GalleryListScreen(navController)
//        }
//    }
//}
//
//@Composable
//private fun GalleryListScreen(navController: NavHostController) {
//    val galleryViewModel: GalleryViewModel by viewModel()
//
//    GalleryListFromActivity(viewModel = galleryViewModel, context = LocalContext.current,)
//}