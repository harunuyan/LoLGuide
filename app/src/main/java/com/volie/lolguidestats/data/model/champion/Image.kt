package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Image(
    val full: String,
    val sprite: String,
    val group: String,
) : Parcelable