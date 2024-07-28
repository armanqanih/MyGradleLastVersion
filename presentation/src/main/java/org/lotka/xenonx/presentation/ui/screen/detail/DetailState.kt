package org.lotka.xenonx.presentation.ui.screen.detail

import org.lotka.xenonx.domain.enams.SnackBarType
import org.lotka.xenonx.domain.model.CoinDetailModel
import org.lotka.xenonx.domain.model.CoinModel

data class DetailState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val coin: CoinDetailModel? = null,
    var snackBarVisible: Boolean = true,
    val snackBarMessage: String = "",
    val snackBarType: SnackBarType = SnackBarType.SUCCESS
)
