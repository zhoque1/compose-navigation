package com.example.mycomposeapplication.ui.screens

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.mycomposeapplication.ui.navigation.NavConstants

@Composable
fun TemplateDetail(navController: NavController) {
    Button(onClick = { navController.navigate(NavConstants.Template.route) }) {
        Text(text = "Back to Template")
    }
}