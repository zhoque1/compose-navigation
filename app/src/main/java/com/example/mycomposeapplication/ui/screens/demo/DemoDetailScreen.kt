package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycomposeapplication.ui.screens.demo.GalleryScreen
import timber.log.Timber

@Composable
fun DemoDetailScreen(
    onClose: () -> Unit,
    onNav: (album: String) -> Unit) {
    Column {
        Button(onClick = onClose) {
            Text(text = "Back to Demo")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Timber.d("test : DemoDetailScreen")
        GalleryScreen(onNav)
    }
}

