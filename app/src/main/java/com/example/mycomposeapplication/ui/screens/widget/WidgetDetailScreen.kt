package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycomposeapplication.ui.navigation.NavConstants
import com.example.mycomposeapplication.ui.GlobalViewModel
import com.example.mycomposeapplication.ui.screens.home.HomeDetailViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun WidgetDetail(navController: NavController, exampleOfRootViewModel: GlobalViewModel) {
    val exampleCount = exampleOfRootViewModel.getRootCount()

    val homeDetailViewModel: HomeDetailViewModel by viewModel()
    val homeDetailCount = homeDetailViewModel.homeDetailCount
    var widgetCount = remember { mutableStateOf(1)}

    Column {
        Button(onClick = { navController.navigate(NavConstants.Widgets.route) }) {
            Text(text = "Back to Widget")
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
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            text = "State remember but forget once you jump on different screen",
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { widgetCount.value += 1 },
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            text = "${widgetCount.value}",
        )
    }
}