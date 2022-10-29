package com.example.mycomposeapplication.ui.screens.demo

import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.example.mycomposeapplication.domain.models.GalleryImage
import com.example.mycomposeapplication.domain.utils.ResultWrapper
import com.example.mycomposeapplication.ui.base.UiEffect
import com.example.mycomposeapplication.ui.base.UiEvent
import com.example.mycomposeapplication.ui.base.UiState
import kotlinx.coroutines.flow.Flow

class GalleryContract{
    // Events that user performed
    sealed class Event : UiEvent {
        object OnInit: Event()
    }

    // Ui View States
    data class State(
        val galleryState: GalleryState
    ) : UiState

    // View States that related to Property Detail
    sealed class GalleryState {
        object Idle: GalleryState()
        data class GalleryResponseSuccess(val gallery: Flow<PagingData<GalleryImage>>) : GalleryState()
        object GalleryResponseError: GalleryState()
    }

    // Side effects
    sealed class Effect : UiEffect {
        //navigation
    }
}
