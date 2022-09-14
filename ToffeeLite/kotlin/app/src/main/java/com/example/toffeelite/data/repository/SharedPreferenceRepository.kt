package com.example.toffeelite.data.repository

import android.content.Context
import android.content.SharedPreferences


const val PREFERENCE_NAME = "ToffeeLite"
const val PREFERENCE_KEY = "CurrentPlaybackPosition"
class SharedPreferenceRepository(context: Context) {

    private val preferences: SharedPreferences? = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getCurrentPlayBackPosition(): Long {
        return preferences?.getLong(PREFERENCE_KEY, 0L) ?: 0L;
    }

    fun setCurrentPlayBackPosition(currentPlaybackPosition: Long){
        val editor = preferences?.edit()
        editor?.putLong(PREFERENCE_KEY, currentPlaybackPosition)
        editor?.apply()
    }
}