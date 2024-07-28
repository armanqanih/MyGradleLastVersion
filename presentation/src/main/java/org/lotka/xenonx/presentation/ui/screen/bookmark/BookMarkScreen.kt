package org.lotka.xenonx.presentation.ui.screen.bookmark

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.lotka.xenonx.presentation.composables.compose.EmptyScreen
import org.lotka.xenonx.presentation.ui.navigation.ScreenNavigation
import org.lotka.xenonx.presentation.ui.screen.home.compose.CoinCard
import org.lotka.xenonx.presentation.util.Dimens.ExtraSmallPadding2
import org.lotka.xenonx.presentation.util.Dimens.MediumPadding1

@Composable
fun BookMarkScreen(
    viewModel: BookMarkViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.state.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        if (state.coins.isEmpty()) {
            EmptyScreen()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(MediumPadding1),
                contentPadding = PaddingValues(all = ExtraSmallPadding2)
            ) {
                items(count = state.coins.size) { index ->
                    state.coins[index]?.let { coin ->
                        CoinCard(coin = coin, onClick = { coinModel ->
                            navController.navigate(ScreenNavigation.DetailRoutScreen.route + "/${coinModel.id}")
                        })
                    }
                }
            }
        }
    }
}
