package com.example.mycomposeapplication.ui.screens.demo

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun GalleryScreen(navController: NavHostController) {
//    val galleryViewModel: GalleryViewModel by viewModel()
//    GalleryList(viewModel = galleryViewModel, context = LocalContext.current)

    GalleryList(context = LocalContext.current)
}