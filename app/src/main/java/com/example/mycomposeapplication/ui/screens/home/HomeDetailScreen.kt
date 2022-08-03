package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycomposeapplication.ui.navigation.NavConstants
import com.example.mycomposeapplication.ui.screens.home.ExampleOfRootViewModel
import com.example.mycomposeapplication.ui.screens.home.HomeDetailViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun HomeDetailScreen(navController: NavController, exampleOfRootViewModel: ExampleOfRootViewModel) {
    val exampleCount = exampleOfRootViewModel.exampleCount

    val homeDetailViewModel: HomeDetailViewModel by viewModel()
    val homeDetailCount = homeDetailViewModel.homeDetailCount

    Column {
        Button(onClick = { navController.navigate(NavConstants.Home.route) }) {
            Text(text = "Back to Home")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { exampleCount.value += 1 },
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            text = "State from root view model",
        )
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = {
                exampleCount.value = 10
            }
        ) {
            Text(text = "Reset me")
        }
        Spacer(modifier = Modifier.size(10.dp))
//        println("Test="+ exampleCount.value.toString())
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { exampleCount.value += 1 },
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            text = "${exampleCount.value}",
        )

        Spacer(modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            text = "State from local view model",
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { homeDetailCount.value += 1 },
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            text = "${homeDetailCount.value}",
        )
        
    }

}
