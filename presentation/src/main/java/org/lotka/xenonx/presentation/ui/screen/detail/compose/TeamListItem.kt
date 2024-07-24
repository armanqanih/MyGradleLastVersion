package org.lotka.xenonx.presentation.ui.screen.detail.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import org.lotka.xenonx.domain.model.TeamMemberModel


@Composable
fun TeamListItem(
    teamMember: TeamMemberModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        if (teamMember.name.isNotEmpty()){
            Text(
                text = teamMember.name,
                style = MaterialTheme.typography.h6
            )
        }else{
            Text(
                text = "No Name",
                style = MaterialTheme.typography.h6
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        if (teamMember.position.isNotEmpty()){
            Text(
                text = teamMember.position,
                style = MaterialTheme.typography.body2,
                fontStyle = FontStyle.Italic
            )
        }else{
            Text(
                text = "No Position",
                style = MaterialTheme.typography.body2,
                fontStyle = FontStyle.Italic,

            )
        }

    }
}