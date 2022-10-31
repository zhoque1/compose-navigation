package com.example.mycomposeapplication.ui.screens.demo.imageitems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.viewModel
import timber.log.Timber


@Composable
fun ShowImageItemsScreen(
    album: String,
    onClose: () -> Unit
) {
    val imageItemsViewModel: ImageItemsViewModel by viewModel()
//    LaunchedEffect(true ){
//        imageItemsViewModel.setEvent(
//            ImageItemsContract.Event.OnInit(album = album.toInt())
//        )
//    }

    Timber.d("test : album = $album")

    Column {
        Button(onClick = onClose) {
            Text(text = "Back to Demo")
        }
        ShowImageItems()
    }


//    when(val state = imageItemsViewModel.uiState.value.imageItemsState){
//        is ImageItemsContract.ImageItemsState.Idle ->{
//            ShowImageItems()
//        }
//        else -> {}
//    }

}
@Composable
private fun ShowImageItems(){
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is a test message")
    }
}