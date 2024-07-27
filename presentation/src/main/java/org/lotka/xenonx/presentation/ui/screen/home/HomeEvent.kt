package org.lotka.xenonx.presentation.ui.screen.home

sealed class HomeEvent {
    object navigate : HomeEvent()
    object GetNextPage : HomeEvent()

}