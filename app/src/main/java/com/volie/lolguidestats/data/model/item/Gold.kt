package com.volie.lolguidestats.data.model.item

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Gold(
    val base: Int,
    val purchasable: Boolean,
    val total: Int,
    val sell: Int
) : Parcelable