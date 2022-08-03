package com.example.mycomposeapplication.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeDetailViewModel (
) : ViewModel() {

    val homeDetailCount: MutableState<Int> = mutableStateOf(1)
}