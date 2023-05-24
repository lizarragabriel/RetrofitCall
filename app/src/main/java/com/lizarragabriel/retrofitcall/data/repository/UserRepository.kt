package com.lizarragabriel.retrofitcall.data.repository

import com.lizarragabriel.retrofitcall.data.local.room.dao.UserDao
import com.lizarragabriel.retrofitcall.data.local.room.entity.User
import javax.inject.Inject


class UserRepository @Inject constructor(private val myDao: UserDao) {
    suspend fun mAllUsers() = myDao.mAllUsers()

    suspend fun mAddUser(user: User) = myDao.mAddUser(user)

    suspend fun mFindOne(username: String) = myDao.mFindOne(username)
}