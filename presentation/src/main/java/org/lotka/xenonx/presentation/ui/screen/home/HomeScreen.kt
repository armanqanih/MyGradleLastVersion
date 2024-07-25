package org.lotka.xenonx.presentation.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

import org.lotka.xenonx.presentation.ui.navigation.ScreenNavigation
import org.lotka.xenonx.presentation.ui.screen.compose.ArmanSearchBar

import org.lotka.xenonx.presentation.ui.screen.home.compose.CoinListItem
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
){

    val state =  viewModel.state.collectAsState().value
    val event = viewModel::onEvent

        LaunchedEffect(key1 =state.isNavigateToCoinDetailScreen) {
           event(HomeEvent.navigateToDetails)
        }




    if (state.isLoading){
        Box(modifier =Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
      if (state.error.isNotEmpty()){
          Box(modifier =Modifier.fillMaxSize(),
              contentAlignment = Alignment.Center
          ) {
              Text(text = state.error)
          }
      }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        ArmanSearchBar(
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navController.navigate(ScreenNavigation.SearchRouteScreen.route)
            }
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        CoinListItem(
            state = state,
            onClick = {coin->
                navController.navigate(ScreenNavigation.DetailRoutScreen.route + "/${coin.id}")
            }
        )
    }





}