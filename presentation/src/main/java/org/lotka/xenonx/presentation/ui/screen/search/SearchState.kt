package org.lotka.xenonx.presentation.ui.screen.search

import org.lotka.xenonx.domain.model.CoinModel

data class SearchState(
    val searchQuery: String = "",
    val coins: List<CoinModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
