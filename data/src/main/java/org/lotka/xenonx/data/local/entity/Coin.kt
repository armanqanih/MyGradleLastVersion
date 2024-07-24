package org.lotka.xenonx.data.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class CoinEntity(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)