package com.example.mycomposeapplication.ui.screens.demo

import androidx.compose.runtime.Composable
import timber.log.Timber

@Composable
fun GalleryScreen(onNav: (album: String) -> Unit) {
//    val galleryViewModel: GalleryViewModel by viewModel()
//    GalleryList(viewModel = galleryViewModel, context = LocalContext.current)

    GalleryList(onNav)
}