package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Passive(
    val name: String? = null,
    val description: String? = null,
    val image: Image? = null
) : Parcelable