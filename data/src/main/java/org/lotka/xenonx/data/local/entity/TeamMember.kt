package org.lotka.xenonx.data.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamMemberModel(
    val id: String,
    val name: String,
    val position: String
): Parcelable