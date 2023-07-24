package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stats(
    val hp: Double,
    val mp: Double,
    val armor: Double,
    @SerializedName("spellblock")
    val spellBlock: Double,
    @SerializedName("attackrange")
    val attackRange: Double,
    @SerializedName("hpregen")
    val hpRegen: Double,
    @SerializedName("mpregen")
    val mpRegen: Double,
    @SerializedName("attackdamage")
    val attackDamage: Double,
) : Parcelable