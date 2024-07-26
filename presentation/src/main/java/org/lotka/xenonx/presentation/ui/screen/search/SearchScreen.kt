package org.lotka.xenonx.presentation.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
//    val event = viewModel::onEvent

    val scaffoldState = rememberScaffoldState()

//    LaunchedEffect(scaffoldState.snackbarHostState) {
//        viewModel.eventFlow.collect { event ->
//            when (event) {
//                is SearchEvent.ShowSnakeBar -> {
//                    scaffoldState.snackbarHostState.showSnackbar(
//                        message = event.message
//                    )
//                }
//
//
//            }
//        }
//    }

    Scaffold(
        scaffoldState = scaffoldState,

    ) {
        Column(
            modifier = Modifier.padding(it)
                .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
                .statusBarsPadding()
        ) {
            ArmanSearchBar(
                text = state.searchQuery,
                onSearch = { viewModel.onEvent(SearchEvent.SearchCoins) },
                onValueChange = { query -> viewModel.onEvent(SearchEvent.UpdateSearchQuery(query)) },
                readOnly = false
            )
            Spacer(modifier = Modifier.height(MediumPadding1))
            state.coins?.let {
                CoinSearchListItem(
                    state = state,
                    onClick = { coin ->
                        navHostController.navigate(ScreenNavigation.DetailRoutScreen.route + "/${coin.id}")
                    }
                )
            }

        }
    }
}