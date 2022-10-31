package com.example.mycomposeapplication.ui.screens.demo.imageitems

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.mycomposeapplication.domain.models.ImageItem
import com.example.mycomposeapplication.ui.components.ZoomableImage
import com.google.accompanist.coil.rememberCoilPainter
import org.koin.androidx.compose.viewModel
import timber.log.Timber


@Composable
fun ShowImageItemsScreen(
    album: String,
    onClose: () -> Unit
) {
    val imageItemsViewModel: ImageItemsViewModel by viewModel()
    LaunchedEffect(true ){
        imageItemsViewModel.setEvent(
            ImageItemsContract.Event.OnInit(album = album.toInt())
        )
    }

    Timber.d("test : album = $album")

    Column {
        Button(onClick = onClose) {
            Text(text = "Back to Demo")
        }
        Spacer(modifier = Modifier.height(5.dp))
        when(val state = imageItemsViewModel.uiState.value.imageItemsState){
            is ImageItemsContract.ImageItemsState.ImageItemsResponseSuccess ->{
                state.imageItems?.let {
                    ShowImageItems(it.imageItems)
                }
            }
            else -> {}
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowImageItems(imageItems: List<ImageItem>) {
    val scroll = rememberScrollState(0)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize()
    ) {
        for (photo in imageItems) {
            photo.url?.let {
                item {
                    val image = rememberCoilPainter(
                        request =  it,
                        fadeIn = true
                    )
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
//                            .height(100.dp)
                            .clip(shape = RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
//                    ZoomableImage(
//                        imageUrl = it,
//                        scrollState = scroll
//                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}