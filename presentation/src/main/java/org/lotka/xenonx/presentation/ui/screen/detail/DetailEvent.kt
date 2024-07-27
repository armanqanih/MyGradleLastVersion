package org.lotka.xenonx.presentation.ui.screen.detail

import org.lotka.xenonx.domain.model.CoinDetailModel
import org.lotka.xenonx.domain.model.CoinModel

sealed class DetailEvent {
    data class UpdateAndDeleterCoin(val coin: CoinDetailModel) : DetailEvent()

    object ShowSnackBar : DetailEvent()
}