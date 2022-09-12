package com.example.mycomposeapplication.ui.screens.widget

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WidgetViewModel (
) : ViewModel() {

    val widgetDetailCount: MutableState<Int> = mutableStateOf(1)
}