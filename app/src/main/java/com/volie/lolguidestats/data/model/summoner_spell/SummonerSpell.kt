package com.volie.lolguidestats.data.model.summoner_spell

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummonerSpell(
    var id: String,
    var name: String,
    var description: String,
    var summonerLevel: Int,
    var modes: List<String>,
) : Parcelable
