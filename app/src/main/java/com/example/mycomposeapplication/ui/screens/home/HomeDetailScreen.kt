package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.mycomposeapplication.ui.navigation.NavConstants
import com.example.mycomposeapplication.ui.screens.home.ExampleViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun HomeDetailScreen(navController: NavController) {
    val exampleViewModel: ExampleViewModel by viewModel()

    val myCount = exampleViewModel.myCount

    Column {
        Button(onClick = { navController.navigate(NavConstants.Home.route) }) {
            Text(text = "Back to Home")
        }
        Spacer(modifier = Modifier.size(20.dp))
//        val myCount: MutableState<Int> =  mutableStateOf(1)
        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            onClick = {
                myCount.value = 10
            }
        ) {
            Text(text = "Reset me")
        }
        Spacer(modifier = Modifier.size(10.dp))
        println("Test="+ myCount.value.toString())
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { myCount.value += 1 },
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            text = "${myCount.value}",
        )
        
    }

}
