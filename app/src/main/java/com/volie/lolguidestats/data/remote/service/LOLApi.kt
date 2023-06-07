package com.volie.lolguidestats.data.remote.service

import com.volie.lolguidestats.data.model.champion.Data
import com.volie.lolguidestats.data.model.item.ItemData
import com.volie.lolguidestats.data.model.map.MapData
import com.volie.lolguidestats.data.model.mission.MissionAssetsData
import com.volie.lolguidestats.data.model.profile_icon.IconData
import com.volie.lolguidestats.data.model.summoner_spell.SummonerSpellData
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
        region: String = "en_US"
    ): Response<ItemData>

    @GET("data/{region}/summoner.json")
    suspend fun getSummonerSpells(
        @Path("region")
        region: String = "en_US"
    ): Response<SummonerSpellData>

    @GET("data/{region}/profileicon.json")
    suspend fun getProfileIcons(
        @Path("region")
        region: String = "en_US"
    ): Response<IconData>

    @GET("data/{region}/map.json")
    suspend fun getMaps(
        @Path("region")
        region: String = "en_US"
    ): Response<MapData>

    @GET("data/{region}/mission-assets.json")
    suspend fun getAssets(
        @Path("region")
        region: String = "en_US"
    ): Response<MissionAssetsData>
}