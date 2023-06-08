package com.volie.lolguidestats.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtil(private val context: Context) {

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    companion object {
        var REGION: String = "en_US"
    }

    fun saveSelectedItem(selectedItem: String) {
        val editor = sharedPreferences.edit()
        editor.putString("selectedItem", selectedItem).apply()
    }

    fun getSelectedItem(): String? {
        return sharedPreferences.getString("selectedItem", null)
    }
}