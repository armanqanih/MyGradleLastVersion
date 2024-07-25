package org.lotka.xenonx.data.local.entity.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import org.lotka.xenonx.data.model.convert.Converters

import org.lotka.xenonx.domain.model.CoinDetailModel



@Entity(tableName = "coins_detail")
@TypeConverters(Converters::class)
data class CoinDetailEntity(
    @PrimaryKey val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMemberEntity>,
    val lastDataAt: String,
)

fun CoinDetailModel.toCoinDetailEntity(): CoinDetailEntity {
    return CoinDetailEntity(
        coinId = coinId,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags,
        team = team.map { it.toTeamMemberEntity() },
        lastDataAt = lastDataAt
    )
}


fun CoinDetailEntity.toCoinDetailModel(): CoinDetailModel {
    return CoinDetailModel(
        coinId = coinId,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags,
        team = team.map { it.toTeamMemberModel() },
        lastDataAt = lastDataAt

    )
}