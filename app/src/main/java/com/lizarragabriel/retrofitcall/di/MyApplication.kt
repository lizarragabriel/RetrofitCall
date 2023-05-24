package com.lizarragabriel.retrofitcall.di

import android.app.Application
import com.lizarragabriel.retrofitcall.data.local.sharedpref.SharedPref
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}