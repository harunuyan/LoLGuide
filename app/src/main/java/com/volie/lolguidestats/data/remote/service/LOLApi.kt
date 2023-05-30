package com.volie.lolguidestats.data.remote.service

import com.volie.lolguidestats.data.model.champion.Data
import com.volie.lolguidestats.data.model.item.ItemData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LOLApi {

    @GET("data/{region}/champion{detail}")
    suspend fun getChampions(
        @Path("region")
        region: String = "en_US",
        @Path("detail")
        champ: String = ".json"
    ): Response<Data>

    @GET("data/{region}/item.json")
    suspend fun getItems(
        @Path("region")
        region: String = "en_US",
    ): Response<ItemData>
}