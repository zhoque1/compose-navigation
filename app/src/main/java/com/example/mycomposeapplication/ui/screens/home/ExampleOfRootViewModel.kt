package com.example.mycomposeapplication.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ExampleOfRootViewModel (
) : ViewModel() {

    val exampleCount: MutableState<Int> = mutableStateOf(1)
}


