package org.lotka.xenonx.data.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



data class CoinDetailEntity(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMemberModel>
)