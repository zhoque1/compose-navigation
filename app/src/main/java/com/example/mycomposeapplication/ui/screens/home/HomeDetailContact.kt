package com.example.mycomposeapplication.ui.screens.home

import com.example.mycomposeapplication.ui.base.UiEffect
import com.example.mycomposeapplication.ui.base.UiEvent
import com.example.mycomposeapplication.ui.base.UiState


class HomeDetailContact {
    // Events that user performs
    sealed class Event: UiEvent {
        object OnIdle : Event()
        object FetchFavoriteKeys : Event()
        object FetchFavoritesPlacards : Event()
        data class OnFavoriteClicked(var propertyKey: String?, var isFavorite: Boolean?, val isFromFavoritePlacards: Boolean = false) : Event()
    }

    // Ui View States
    data class State(
        val favoritesState: FavoritesState,
        val favoritePlacardsState: FavoritePlacardsState
    ) : UiState

    // This is a state only for favorite list screen only
    sealed class FavoritePlacardsState{
        object Idle: FavoritePlacardsState()
        object ReportError: FavoritePlacardsState()
    }

    // This is a global state to know whether a listing is favorite or not
    sealed class FavoritesState {
        object Idle: FavoritesState()
        object GetFavoritePropertyKeys : FavoritesState()
        object ReportError: FavoritesState()
    }

    // Side effects
    sealed class Effect: UiEffect {}
}