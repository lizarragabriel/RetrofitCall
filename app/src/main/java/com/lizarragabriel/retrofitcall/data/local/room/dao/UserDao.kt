package com.lizarragabriel.retrofitcall.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lizarragabriel.retrofitcall.data.local.room.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun mAllUsers(): List<User>

    @Insert
    suspend fun mAddUser(user: User)

    @Query("SELECT * FROM users WHERE :username = username")
    suspend fun mFindOne(username: String): User?
}