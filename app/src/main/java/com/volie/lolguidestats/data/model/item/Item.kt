package com.volie.lolguidestats.data.model.item

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.volie.lolguidestats.data.model.image.Image
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val name: String? = null,
    val description: String? = null,
    val plaintext: String? = null,
    val into: List<String>? = null,
    @SerializedName("image")
    val itemImage: Image,
    val gold: Gold? = null,
    val from: List<String>? = null,
    val tags: List<String>? = null
) : Parcelable