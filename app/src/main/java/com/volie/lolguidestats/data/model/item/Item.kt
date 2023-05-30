package com.volie.lolguidestats.data.model.item

import com.google.gson.annotations.SerializedName

data class Item(
    val name: String,
    val description: String,
    val plaintext: String,
    val into: List<String>,
    @SerializedName("image")
    val itemImage: ItemImage,
    val gold: Gold,
    val from: List<String>,
    val tags: List<String>,
    @SerializedName("stats")
    val itemStats: Map<String, Double>,
    val maps: Map<String, Boolean>
)