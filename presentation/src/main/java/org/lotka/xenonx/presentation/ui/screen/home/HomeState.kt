package org.lotka.xenonx.presentation.ui.screen.home

import org.lotka.xenonx.domain.model.CoinModel

data class HomeState(
    val isLoading: Boolean = false,
    val coins: List<CoinModel> = emptyList(),
    val error: String = "",
    val page: Int = 1,
    val isNavigateToCoinDetailScreen: Boolean = false,
)
