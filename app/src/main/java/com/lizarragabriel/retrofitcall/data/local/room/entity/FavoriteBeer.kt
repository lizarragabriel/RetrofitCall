package com.lizarragabriel.retrofitcall.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteBeer(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val userid: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val image_url: String,
    var rating: Int = 0
) {
}