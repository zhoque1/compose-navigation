package com.example.mycomposeapplication.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GlobalViewModel (
) : ViewModel() {

    private val exampleCount: MutableState<Int> = mutableStateOf(1)
    fun getRootCount(): MutableState<Int>{
        return exampleCount
    }
}


