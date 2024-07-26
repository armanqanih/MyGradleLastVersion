package org.lotka.xenonx.presentation.ui.screen.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val query: String) : SearchEvent()
    data class ShowSnakeBar(val message: String) : SearchEvent()
    object SearchCoins : SearchEvent()
}