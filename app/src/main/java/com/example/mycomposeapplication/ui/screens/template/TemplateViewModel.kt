package com.example.mycomposeapplication.ui.screens.template

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TemplateViewModel (
) : ViewModel() {

    val templateDetailCount: MutableState<Int> = mutableStateOf(1)
}