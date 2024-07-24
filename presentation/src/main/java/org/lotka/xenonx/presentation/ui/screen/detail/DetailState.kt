package org.lotka.xenonx.presentation.ui.screen.detail

import org.lotka.xenonx.domain.model.CoinDetailModel
import org.lotka.xenonx.domain.model.CoinModel

data class DetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val coin: CoinDetailModel? = null,
)
