package com.lizarragabriel.retrofitcall.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lizarragabriel.retrofitcall.data.local.room.dao.BeerDao
import com.lizarragabriel.retrofitcall.data.local.room.dao.UserDao
import com.lizarragabriel.retrofitcall.data.local.room.entity.FavoriteBeer
import com.lizarragabriel.retrofitcall.data.local.room.entity.User

@Database(entities = [User::class, FavoriteBeer::class], version = 1)

abstract class AppDatabase: RoomDatabase() {
    abstract fun mUserDao(): UserDao
    abstract fun mBeerDao(): BeerDao
}