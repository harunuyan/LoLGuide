package com.volie.lolguidestats.data.model.profile_icon

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IconData(
    val data: Map<String, Icon>
) : Parcelable
