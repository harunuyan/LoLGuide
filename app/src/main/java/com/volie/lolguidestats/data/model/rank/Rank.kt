package com.volie.lolguidestats.data.model.rank

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rank(
    val rankName: String,
    val url: String
) : Parcelable