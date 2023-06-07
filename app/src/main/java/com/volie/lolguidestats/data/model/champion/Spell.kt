package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import com.volie.lolguidestats.data.model.image.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class Spell(
    val id: String,
    val name: String,
    val description: String,
    val tooltip: String,
    val maxrank: Int,
    val cooldown: List<Double>,
    val cooldownBurn: String,
    val cost: List<Int>,
    val costBurn: String,
    val costType: String,
    val maxammo: String,
    val range: List<Int>,
    val rangeBurn: String,
    val image: Image,
    val resource: String
) : Parcelable