package com.volie.lolguidestats.data.model.champion

import android.os.Parcelable
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
    val allytips: List<String>? = null,
    val enemytips: List<String>? = null,
    val tags: List<String>? = null,
    val partype: String? = null,
    val stats: Stats? = null,
    val spells: List<Spell>? = null,
) : Parcelable








