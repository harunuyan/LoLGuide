package com.volie.lolguidestats.data.model.item

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val name: String? = null,
    val description: String? = null,
    val plaintext: String? = null,
    val into: List<String>,
    @SerializedName("image")
    val itemImage: ItemImage? = null,
    val gold: Gold? = null,
    val from: List<String>? = null,
    val tags: List<String>? = null,
    @SerializedName("stats")
    val itemStats: Map<String, Double>? = null,
    val maps: Map<String, Boolean>? = null,
) : Parcelable