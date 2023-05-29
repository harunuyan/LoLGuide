package com.volie.lolguidestats.data.remote.service

import com.volie.lolguidestats.data.model.champion.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LOLApi {

    @GET("data/{region}/champion{champ}")
    suspend fun getChampions(
        @Path("region")
        region: String = "en_US",
        @Path("champ")
        champ: String = ".json"
    ): Response<Data>
}