package com.lizarragabriel.pruebaandroid.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lizarragabriel.pruebaandroid.data.local.room.dao.BeerDao
import com.lizarragabriel.pruebaandroid.data.local.room.dao.UserDao
import com.lizarragabriel.pruebaandroid.data.local.room.entity.FavoriteBeer
import com.lizarragabriel.pruebaandroid.data.local.room.entity.User

@Database(entities = [User::class, FavoriteBeer::class], version = 1)

abstract class AppDatabase: RoomDatabase() {
    abstract fun mUserDao(): UserDao
    abstract fun mBeerDao(): BeerDao
}