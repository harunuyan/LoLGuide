package com.volie.lolguidestats.data.model.summoner_spell

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.volie.lolguidestats.data.model.champion.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummonerSpell(
    var id: String,
    var name: String,
    var description: String,
    var tooltip: String,
    @SerializedName("maxrank")
    var maxRank: Int,
    var cooldown: List<Double>,
    var cooldownBurn: String,
    var summonerLevel: Int,
    var modes: List<String>,
    var range: List<Int>,
    var rangeBurn: String,
    var image: Image
) : Parcelable
