package com.lizarragabriel.retrofitcall.data.local.room.dao

import androidx.room.*
import com.lizarragabriel.retrofitcall.data.local.room.entity.FavoriteBeer

@Dao
interface BeerDao {
    @Query("SELECT * FROM favorites WHERE :userid = userid")
    suspend fun mAllBeers(userid: Int): List<FavoriteBeer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun mAddFavorite(beerFavoriteBeer: FavoriteBeer)

    @Delete
    suspend fun mDeleteFavorite(beerFavoriteBeer: FavoriteBeer)

    @Update
    suspend fun mUpdateFavorite(beerFavoriteBeer: FavoriteBeer)

}