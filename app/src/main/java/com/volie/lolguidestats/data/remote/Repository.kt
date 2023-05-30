package com.volie.lolguidestats.data.remote

import com.volie.lolguidestats.data.model.champion.Data
import com.volie.lolguidestats.data.model.item.ItemData
import com.volie.lolguidestats.data.remote.service.LOLApi
import com.volie.lolguidestats.helper.Resource
import javax.inject.Inject

class Repository
@Inject constructor(
    private val service: LOLApi
) {

    suspend fun getChampions(detail: String): Resource<Data> {
        return try {
            val response = service.getChampions(champ = detail)
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
}