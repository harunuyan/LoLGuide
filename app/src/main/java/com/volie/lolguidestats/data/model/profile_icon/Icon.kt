package com.volie.lolguidestats.data.model.profile_icon

import android.os.Parcelable
import com.volie.lolguidestats.data.model.champion.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class Icon(
    val image: Image
) : Parcelable
