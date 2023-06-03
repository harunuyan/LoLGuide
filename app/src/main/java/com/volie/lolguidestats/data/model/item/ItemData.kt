package com.volie.lolguidestats.data.model.item

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemData(
    var data: Map<String, Item>
) : Parcelable
