package org.lotka.xenonx.data.local.entity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.lotka.xenonx.domain.model.TeamMemberModel


data class TeamMemberEntity(
   val id: String,
    val name: String,
    val position: String
)
fun TeamMemberModel.toTeamMemberEntity(): TeamMemberEntity {
    return TeamMemberEntity(
        id = id,
        name = name,
        position = position
    )
}



fun TeamMemberEntity.toTeamMemberModel(): TeamMemberModel {
    return TeamMemberModel(
        id = id,
        name = name,
        position = position
    )
}