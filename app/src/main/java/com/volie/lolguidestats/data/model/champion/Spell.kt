package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import com.volie.lolguidestats.data.model.image.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class Spell(
    val name: String,
    val description: String,
    val image: Image,
) : Parcelable