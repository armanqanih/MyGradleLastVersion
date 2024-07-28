package org.lotka.xenonx.presentation.ui.screen.home

import BottomNavigationBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor

import org.lotka.xenonx.presentation.ui.navigation.ScreenNavigation
import org.lotka.xenonx.presentation.ui.screen.home.compose.CoinCard

import org.lotka.xenonx.presentation.util.Dimens.ExtraSmallPadding2
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
   viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val  state = viewModel.state.collectAsState().value
    val  event = viewModel::onEvent


//    LaunchedEffect(key1 = state.isNavigateToNewPage) {
//        event(HomeEvent.navigate)
//    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
            content = { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(top = MediumPadding1)
                    .statusBarsPadding()
            ) {

                    Spacer(modifier = Modifier.height(MediumPadding1))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(MediumPadding1),
                    contentPadding = PaddingValues(all = ExtraSmallPadding2)
                ) {
                    items(count = state.coins.size) {
                        state.coins[it]?.let { coin ->
                            CoinCard(coin = coin, onClick = { coins ->
                                navController.navigate(ScreenNavigation.DetailRoutScreen.route + "/${coins.id}")
                            })
                        }
                    }
                }
            }

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = kilidPrimaryColor,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(50.dp),
                        strokeWidth = 8.dp,
                        strokeCap = StrokeCap.Round,
                        backgroundColor = Color.White,
                    )
                }
            }

            if (state.error.isNotEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error,
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    )
}






