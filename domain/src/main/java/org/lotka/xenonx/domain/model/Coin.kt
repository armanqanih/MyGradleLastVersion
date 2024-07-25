package org.lotka.xenonx.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinModel(
    val id: String,
    val isActive: Boolean?,
    val name: String?,
    val rank: Int?,
    val symbol: String?,
    val type: String?=null,
    val isNew: Boolean?=null,
): Parcelable