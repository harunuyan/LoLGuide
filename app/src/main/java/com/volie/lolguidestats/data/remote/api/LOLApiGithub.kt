package com.volie.lolguidestats.data.remote.api

import com.volie.lolguidestats.data.model.mode.ModeData
import com.volie.lolguidestats.data.model.rank.SeasonData
import retrofit2.Response
import retrofit2.http.GET

interface LOLApiGithub {

    @GET("gameModes.json")
    suspend fun getGameModes(): Response<ModeData>

    @GET("rank.json")
    suspend fun getRanks(): Response<SeasonData>
}