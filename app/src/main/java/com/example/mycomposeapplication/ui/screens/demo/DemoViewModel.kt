package com.example.mycomposeapplication.ui.screens.demo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DemoViewModel (
) : ViewModel() {

    val demoDetailCount: MutableState<Int> = mutableStateOf(1)
}