package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycomposeapplication.ui.navigation.NavConstants
import com.example.mycomposeapplication.ui.GlobalViewModel

@Composable
fun DemoDetail(navController: NavController, exampleOfRootViewModel: GlobalViewModel) {
    val exampleCount = exampleOfRootViewModel.getRootCount()
    Column {
        Button(onClick = { navController.navigate(NavConstants.DemoUI.route) }) {
            Text(text = "Back to Demo")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { exampleCount.value += 1 },
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            text = "${exampleCount.value}",
        )
    }
}