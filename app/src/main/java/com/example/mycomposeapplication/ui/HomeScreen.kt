package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mycomposeapplication.ui.navigation.NavConstants
import com.example.mycomposeapplication.ui.navigation.ScaffoldScreen
import com.example.mycomposeapplication.ui.navigation.SubNavConstants

@Composable
fun HomeScreen(navController: NavHostController) {
    ScaffoldScreen(navController = navController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Home Screen")
            Button(onClick = { navController.navigate(SubNavConstants.HomeDetail.route) }) {
                Text(text = "Navigate to detail")
            }
            Spacer(modifier = Modifier.size(5.dp))
            Button(onClick = { navController.navigate(SubNavConstants.StateExample.route) }) {
                Text(text = "Navigate to State example")
            }
        }
    }
}