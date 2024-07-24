package org.lotka.xenonx.presentation.ui.screen.home.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.presentation.theme.GrayLight
import org.lotka.xenonx.presentation.theme.Green
import org.lotka.xenonx.presentation.theme.Magenta
import org.lotka.xenonx.presentation.theme.PrimaryDark
import org.lotka.xenonx.presentation.theme.Red
import org.lotka.xenonx.presentation.theme.coolGray

@Composable
fun CoinCard(
    modifier: Modifier = Modifier,
    coin: CoinModel,
    onClick: (CoinModel) -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp)).
            clickable { onClick.invoke(coin) },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = "${coin.rank} ${coin.name} ${coin.symbol}  ",
                style = androidx.compose.material.MaterialTheme.typography.body1,
                color =  Color.Black // default color if rank is not 1 or 2
               ,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)

            )
            Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = coin.isActive.let {
                        if (it == true) "Active" else "Inactive"

                    } ,
                    style = androidx.compose.material.MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                        ),
                    color = coin.isActive.let {
                        if (it == true) Color.Green
                        else Color.Red
                    },
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )

    }
}

