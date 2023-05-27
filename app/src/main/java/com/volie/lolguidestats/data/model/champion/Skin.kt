package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Skin(
    val id: String,
    val num: Int,
    val name: String,
    val chromas: Boolean
) : Parcelable