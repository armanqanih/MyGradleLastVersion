package org.lotka.xenonx.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.lotka.xenonx.domain.model.CoinModel

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey val id: String,
    val isActive: Boolean?,
    val name: String?,
    val rank: Int?,
    val symbol: String?,
)

fun CoinModel.toCoinEntity(): CoinEntity {
    return CoinEntity(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}


fun CoinEntity.toCoinModel(): CoinModel {
    return CoinModel(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol,

    )
}