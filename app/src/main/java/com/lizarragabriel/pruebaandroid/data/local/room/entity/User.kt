package com.lizarragabriel.pruebaandroid.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val username: String,
    val password: String
)
