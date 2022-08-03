package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycomposeapplication.ui.navigation.NavConstants

@Composable
fun StateExampleScreen(navController: NavController) {
    Column {
        Button(onClick = { navController.navigate(NavConstants.Home.route) }) {
            Text(text = "Back to Home")
        }
        HelloScreen("test")
    }
}

@Composable
private fun HelloScreen(name1: String) {
    var name = remember { mutableStateOf(name1) }
    HelloContent(name = name.value, onNameChange = { name.value = it })
}

@Composable
private fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Separate state from content and move it to top",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
        )
    }
}
