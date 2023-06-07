package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import com.volie.lolguidestats.data.model.image.Image
import kotlinx.parcelize.Parcelize

@Parcelize
class Passive(
    val name: String? = null,
    val description: String? = null,
    val image: Image? = null
) : Parcelable