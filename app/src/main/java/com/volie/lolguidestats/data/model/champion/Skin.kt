package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skin(
    val name: String,
) : Parcelable