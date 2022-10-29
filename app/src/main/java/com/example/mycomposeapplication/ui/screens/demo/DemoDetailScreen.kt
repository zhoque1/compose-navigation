package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mycomposeapplication.ui.navigation.NavConstants
import com.example.mycomposeapplication.ui.GlobalViewModel
import com.example.mycomposeapplication.ui.screens.demo.GalleryScreen

@Composable
fun DemoDetailScreen(navController: NavHostController, exampleOfRootViewModel: GlobalViewModel) {
    Column {
        Button(onClick = { navController.navigate(NavConstants.DemoUI.route) }) {
            Text(text = "Back to Demo")
        }
        Spacer(modifier = Modifier.size(20.dp))
        GalleryScreen(
            navController = navController
        )
    }
}

