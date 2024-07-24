package org.lotka.xenonx.presentation.ui.screen.home

sealed class HomeEvent {
    object navigateToDetails : HomeEvent()
    object GetNextPage : HomeEvent()

}