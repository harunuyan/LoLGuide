package com.volie.lolguidestats.data.remote

import android.util.Log
import com.volie.lolguidestats.data.model.champion.Data
import com.volie.lolguidestats.data.model.item.ItemData
import com.volie.lolguidestats.data.model.map.MapData
import com.volie.lolguidestats.data.model.mission.MissionAssetsData
import com.volie.lolguidestats.data.model.mode.ModeData
import com.volie.lolguidestats.data.model.profile_icon.IconData
import com.volie.lolguidestats.data.model.rank.SeasonData
import com.volie.lolguidestats.data.model.summoner_spell.SummonerSpellData
import com.volie.lolguidestats.data.remote.service.LOLApi
import com.volie.lolguidestats.data.remote.service.LOLApiGithub
import com.volie.lolguidestats.helper.Resource
import javax.inject.Inject

class Repository
@Inject constructor(
    private val service: LOLApi,
    private val serviceGithub: LOLApiGithub
) {

    suspend fun getChampions(): Resource<Data> {
        return try {
            val response = service.getChampions()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error*1", null)
            } else {
                Resource.error("Error*2", null)
            }
        } catch (e: Exception) {
            Resource.error("$e", null)
        }
    }

    suspend fun getItems(): Resource<ItemData> {
        return try {
            val response = service.getItems()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error*1", null)
            } else {
                Resource.error("Error*2", null)
            }
        } catch (e: Exception) {
            Resource.error("$e", null)
        }
    }

    suspend fun getSummonerSpell(): Resource<SummonerSpellData> {
        return try {
            val response = service.getSummonerSpells()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error*1", null)
            } else {
                Resource.error("Error*2", null)
            }
        } catch (e: Exception) {
            Resource.error("$e", null)
        }
    }

    suspend fun getProfileIcons(): Resource<IconData> {
        return try {
            val response = service.getProfileIcons()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error*1", null)
            } else {
                Resource.error("Error*2", null)
            }
        } catch (e: Exception) {
            Resource.error("$e", null)
        }
    }

    suspend fun getGameModes(): Resource<ModeData> {
        return try {
            val response = serviceGithub.getGameModes()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error*1", null)
            } else {
                Resource.error("Error*2", null)
            }
        } catch (e: Exception) {
            Resource.error("$e", null)
        }
    }

    suspend fun getMaps(): Resource<MapData> {
        return try {
            val response = service.getMaps()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error*1", null)
            } else {
                Resource.error("Error*2", null)
            }
        } catch (e: Exception) {
            Resource.error("$e", null)
        }
    }

    suspend fun getRank(): Resource<SeasonData> {
        return try {
            val response = serviceGithub.getRanks()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error*1", null)
            } else {
                Resource.error("Error*2", null)
            }
        } catch (e: Exception) {
            Log.e("Error", "$e")
            Resource.error("$e", null)
        }
    }

    suspend fun getAssets(): Resource<MissionAssetsData> {
        return try {
            val response = service.getAssets()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error*1", null)
            } else {
                Resource.error("Error*2", null)
            }
        } catch (e: Exception) {
            Log.e("Error", "$e")
            Resource.error("$e", null)
        }
    }
}