package org.lotka.xenonx.presentation.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.lotka.xenonx.presentation.ui.navigation.ScreenNavigation
import org.lotka.xenonx.presentation.ui.screen.compose.ArmanSearchBar
import org.lotka.xenonx.presentation.ui.screen.search.compose.CoinSearchListItem
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1

@Composable
fun SearchScreen(
    navHostController: NavHostController
){

    val viewModel: SearchViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value
    val event = viewModel::onEvent

    Column(
        modifier = Modifier
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
            .statusBarsPadding()
    ) {
        ArmanSearchBar(
            text = state.searchQuery,
            onSearch = {event(SearchEvent.SearchCoins) },
            onValueChange = {event(SearchEvent.UpdateSearchQuery(it))}    ,
            readOnly = false
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.coins?.let {
            CoinSearchListItem(
               state = state,
                onClick = {coin->
                    navHostController.navigate(ScreenNavigation.DetailRoutScreen.route + "/${coin.id}") }
            )
        }

    }

}