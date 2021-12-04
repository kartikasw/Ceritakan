package com.example.perempuan.activity

import android.content.Context
import android.content.SharedPreferences

class SaveData(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)

    fun setDarkModeState(state: Boolean) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putBoolean("Dark", state)
        editor.apply()
    }

    fun loadDarkModeState(): Boolean {
        val state: Boolean = sharedPref.getBoolean("Dark", false)
        return state
    }
}