package com.volie.lolguidestats.data.model.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Image(
    val full: String
) : Parcelable