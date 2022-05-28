package com.example.mycomposeapplication.ui.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mycomposeapplication.ui.navigation.NavConstants

@Composable
fun DemoDetail(navController: NavController) {
    Button(onClick = { navController.navigate(NavConstants.DemoUI.route) }) {
        Text(text = "Back to Demo")
    }
}