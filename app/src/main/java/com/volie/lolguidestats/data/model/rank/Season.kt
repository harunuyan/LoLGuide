package com.volie.lolguidestats.data.model.rank

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    val image: String,
    val name: String,
    val rank: List<Rank>
) : Parcelable