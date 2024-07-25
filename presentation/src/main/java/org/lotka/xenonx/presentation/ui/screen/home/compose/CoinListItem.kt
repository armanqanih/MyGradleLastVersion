package org.lotka.xenonx.presentation.ui.screen.home.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.presentation.ui.screen.home.HomeState
import org.lotka.xenonx.presentation.util.Dimens.ExtraSmallPadding2
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1

@Composable
fun CoinListItem(
    state: HomeState,
    onClick: (CoinModel) -> Unit
) {


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(MediumPadding1),
        contentPadding = PaddingValues(all = ExtraSmallPadding2)
    ) {

        items(count = state.coins.size) {
            state.coins[it]?.let { coin ->
                CoinCard(coin = coin, onClick = { onClick(coin) })
            }
        }
    }
}