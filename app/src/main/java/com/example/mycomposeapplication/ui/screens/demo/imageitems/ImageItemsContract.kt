package com.example.mycomposeapplication.ui.screens.demo.imageitems

import com.example.mycomposeapplication.domain.models.ImageItems
import com.example.mycomposeapplication.ui.base.UiEffect
import com.example.mycomposeapplication.ui.base.UiEvent
import com.example.mycomposeapplication.ui.base.UiState

class ImageItemsContract {
    // Events that user performed
    sealed class Event : UiEvent {
        data class OnInit(val album: Int) : Event()
    }

    // Ui View States
    data class State(
        val imageItemsState: ImageItemsState
    ) : UiState

    // View States that related to Property Detail
    sealed class ImageItemsState {
        object Idle : ImageItemsState()
        data class ImageItemsResponseSuccess(val imageItems: ImageItems?) : ImageItemsState()
        object ImageItemsResponseError : ImageItemsState()
    }

    // Side effects
    sealed class Effect : UiEffect {
        //navigation
    }
}