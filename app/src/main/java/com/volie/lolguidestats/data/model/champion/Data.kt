package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val data: Map<String, Champion>
) : Parcelable

