package com.volie.lolguidestats.data.model.mode

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModeData(
    val modes: List<Mode>,
) : Parcelable