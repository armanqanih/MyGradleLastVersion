package org.lotka.xenonx.presentation.ui.screen.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.flowlayout.FlowRow
import kotlinx.coroutines.delay
import org.lotka.xenonx.presentation.composables.CoinSnackBar
import org.lotka.xenonx.presentation.theme.PrimaryDark
import org.lotka.xenonx.presentation.theme.gold
import org.lotka.xenonx.presentation.theme.kilidPrimaryColor
import org.lotka.xenonx.presentation.ui.screen.detail.compose.CoinTag
import org.lotka.xenonx.presentation.ui.screen.detail.compose.DetailsTopBar
import org.lotka.xenonx.presentation.ui.screen.detail.compose.TeamListItem


@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current


    LaunchedEffect(key1 = state.snackBarVisible) {
        if (state.snackBarVisible) {
            delay(1000L)
            viewModel.onEvent(DetailEvent.ShowSnackBar) // Reset the snackbar visibility after showing
        }
    }

   CoinSnackBar(
        visible =  state.snackBarVisible,
        text = state.snackBarMessage,
        onDismiss = { viewModel.onEvent(DetailEvent.ShowSnackBar) },
        snackBarType = state.snackBarType
    )



    Scaffold (
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {

        },
        topBar = {
            if(!state.isLoading) {

                DetailsTopBar(
                    onBrowsingClick = {
                        Intent(Intent.ACTION_VIEW).also {
                            it.data = Uri.parse(state.coin?.coinId)
                            if (it.resolveActivity(context.packageManager) != null) {
                                context.startActivity(it)
                            }
                        }
                    },
                    onShareClick = {
                        Intent(Intent.ACTION_SEND).also {
                            it.putExtra(Intent.EXTRA_TEXT,state.coin?.coinId)
                            it.type = "text/plain"
                            if (it.resolveActivity(context.packageManager) != null) {
                                context.startActivity(it)
                            }
                        }
                    },
                    onBookMarkClick = {
                     viewModel.onEvent(DetailEvent.UpdateAndDeleterCoin(state.coin!!))
                    },
                    onBackClick = navHostController::navigateUp
                )


            }


        },
        content = {
            Box(modifier = Modifier.fillMaxSize().padding(it)) {
                state.coin?.let { coin ->
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(20.dp)
                    ) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                    style = MaterialTheme.typography.h5,
                                    modifier = Modifier.weight(8f),
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = if(coin.isActive) "active" else "inactive",
                                    color = if(coin.isActive) Color.Green else Color.Red,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier
                                        .align(CenterVertically)
                                        .weight(2f)
                                )
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = coin.description,
                                style = MaterialTheme.typography.body2
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "Last Data At: ${coin.lastDataAt}",
                                style = MaterialTheme.typography.body2,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "Tags",
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            FlowRow(
                                mainAxisSpacing = 10.dp,
                                crossAxisSpacing = 10.dp,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                coin.tags.forEach { tag ->
                                    CoinTag(tag = tag)
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "Team members",
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                        items(coin.team) { teamMember ->
                            TeamListItem(
                                teamMember = teamMember,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            )
                            Divider()

                        }
                    }


                }
                if(state.error?.isNotBlank() == true) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if(state.isLoading) {
                    CircularProgressIndicator(
                        color = kilidPrimaryColor,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(50.dp)
                        ,
                        strokeWidth = 8.dp,
                        strokeCap = StrokeCap.Round,
                        backgroundColor = Color.White,


                        )
                }
            }
        }

    )



}