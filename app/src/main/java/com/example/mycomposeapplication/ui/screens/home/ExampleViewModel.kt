package com.example.mycomposeapplication.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ExampleViewModel (
) : ViewModel() {

    val myCount: MutableState<Int> = mutableStateOf(1)
}


