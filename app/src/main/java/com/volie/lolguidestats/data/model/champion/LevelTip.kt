package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LevelTip(
    val label: List<String>,
    val effect: List<String>
) : Parcelable