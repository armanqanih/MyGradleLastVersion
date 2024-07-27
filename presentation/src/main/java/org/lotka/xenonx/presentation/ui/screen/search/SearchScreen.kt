package org.lotka.xenonx.presentation.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.lotka.xenonx.presentation.ui.navigation.ScreenNavigation
import org.lotka.xenonx.presentation.ui.screen.compose.ArmanSearchBar
import org.lotka.xenonx.presentation.ui.screen.search.compose.CoinSearchListItem
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController
) {

    val viewModel: SearchViewModel = hiltViewModel()
    val state = viewModel.state.collectAsState().value


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
            modifier = Modifier
                .padding(it)
                .padding(4.dp)
                .statusBarsPadding()
        ) {

            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = state.searchQuery,
                onQueryChange = { query -> viewModel.onEvent(SearchEvent.UpdateSearchQuery(query)) },
                onSearch = { viewModel.onEvent(SearchEvent.SearchCoins) },
                active = state.searchActive,
                onActiveChange = {
                    state.searchActive = it
                },
                placeholder = {
                    Text(text = "Search....")
                },

                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                trailingIcon = {
                    if (state.searchActive) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (state.searchQuery.isNotEmpty()) {
                                    viewModel.onEvent(SearchEvent.UpdateSearchQuery(""))
                                }else{
                                    state.searchActive = false
                                }

                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon"
                        )
                    }
                },
                colors = SearchBarDefaults.colors(
                    containerColor = SearchBarDefaults.colors().containerColor.copy(alpha = 0.5f)
                )

            ) {

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
}