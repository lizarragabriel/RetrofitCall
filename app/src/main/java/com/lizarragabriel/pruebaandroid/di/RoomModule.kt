package com.lizarragabriel.pruebaandroid.di

import android.content.Context
import androidx.room.Room
import com.lizarragabriel.pruebaandroid.data.local.room.dao.BeerDao
import com.lizarragabriel.pruebaandroid.data.local.room.dao.UserDao
import com.lizarragabriel.pruebaandroid.data.local.room.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DB_NAME = "pruebaandroid.db"

    @Singleton
    @Provides
    fun mProvideRoom(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    }

    @Singleton
    @Provides
    fun mProvideDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.mUserDao()
    }

    @Singleton
    @Provides
    fun mProvideBeerDao(appDatabase: AppDatabase): BeerDao {
        return appDatabase.mBeerDao()
    }
}