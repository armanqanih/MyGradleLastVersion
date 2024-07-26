package org.lotka.xenonx.presentation.ui.screen.search.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.presentation.ui.screen.home.compose.CoinCard
import org.lotka.xenonx.presentation.ui.screen.search.SearchState
import org.lotka.xenonx.presentation.util.Dimens.ExtraSmallPadding2
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1

@Composable
fun CoinSearchListItem(
    state: SearchState,
    onClick: (CoinModel) -> Unit
) {


    state.coins?.let { coins ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = coins.size) { eachCoin ->
                if (eachCoin > 0) {
                    Spacer(modifier = Modifier.height(10.dp))
                }

                coins[eachCoin]?.let { coin ->
                        CoinCard(coin = coin, onClick = { onClick.invoke(coin) })
                    }

                if (eachCoin == coins.size - 1) {
                    Divider()
                }

            }
        }
    }
}