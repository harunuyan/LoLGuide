package com.volie.lolguidestats.data.model.map

import com.google.gson.annotations.SerializedName
import com.volie.lolguidestats.data.model.image.Image

data class Map(
    @SerializedName("MapName")
    val mapName: String,
    val image: Image,
)
