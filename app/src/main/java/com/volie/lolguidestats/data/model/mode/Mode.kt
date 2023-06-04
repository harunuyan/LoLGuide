package com.volie.lolguidestats.data.model.mode

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mode(
    val gameMode: String,
    val description: String,
    val url: String
) : Parcelable
