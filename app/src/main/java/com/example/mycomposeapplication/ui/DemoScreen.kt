package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.mycomposeapplication.ui.navigation.NavConstants
import com.example.mycomposeapplication.ui.navigation.ScaffoldScreen
import com.example.mycomposeapplication.ui.navigation.SubNavConstants

@Composable
fun DemoScreen(navController: NavHostController) {
    ScaffoldScreen(navController = navController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Demo Screen")
            Button(onClick = { navController.navigate(SubNavConstants.DemoUIDetail.route) }) {
                Text(text = "Navigate to detail")
            }
        }
    }
}