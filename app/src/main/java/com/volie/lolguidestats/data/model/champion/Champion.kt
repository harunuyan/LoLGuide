package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.volie.lolguidestats.data.model.image.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class Champion(
    val id: String? = null,
    val name: String? = null,
    val title: String? = null,
    val blurb: String? = null,
    val info: Info? = null,
    val image: Image? = null,
    val skins: List<Skin>? = null,
    val lore: String? = null,
    val passive: Passive? = null,
    @SerializedName("allytips")
    val allyTips: List<String>? = null,
    @SerializedName("enemytips")
    val enemyTips: List<String>? = null,
    val tags: List<String>? = null,
    val stats: Stats? = null,
    val spells: List<Spell>? = null,
) : Parcelable








