package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    val attack: Int,
    val defense: Int,
    val magic: Int,
    val difficulty: Int
) : Parcelable