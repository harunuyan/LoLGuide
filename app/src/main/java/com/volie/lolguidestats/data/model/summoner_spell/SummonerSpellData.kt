package com.volie.lolguidestats.data.model.summoner_spell

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummonerSpellData(
    var data: Map<String, SummonerSpell>
) : Parcelable