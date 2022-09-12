package com.example.mycomposeapplication.ui.screens.animation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AnimationViewModel (
) : ViewModel() {

    val animationDetailCount: MutableState<Int> = mutableStateOf(1)
}