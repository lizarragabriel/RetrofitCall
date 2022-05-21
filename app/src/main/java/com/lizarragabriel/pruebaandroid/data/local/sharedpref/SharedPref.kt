package com.lizarragabriel.pruebaandroid.data.local.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.lizarragabriel.pruebaandroid.data.remote.Beer

object SharedPref {

    lateinit var beer: Beer
    private lateinit var myShared: SharedPreferences
    private const val SESSION = "session"

    fun init(context: Context) {
        myShared = context.getSharedPreferences("mysharedpref", Context.MODE_PRIVATE)
    }

    fun setSession(userID: Int) {
        myShared.edit().putInt(SESSION, userID).apply()
    }

    fun getSession() = myShared.getInt(SESSION, 0)
}