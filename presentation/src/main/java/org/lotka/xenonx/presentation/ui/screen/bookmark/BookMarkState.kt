package org.lotka.xenonx.presentation.ui.screen.bookmark

import org.lotka.xenonx.domain.model.CoinModel

data class BookMarkState (
    val coins: List<CoinModel> = emptyList(),
)