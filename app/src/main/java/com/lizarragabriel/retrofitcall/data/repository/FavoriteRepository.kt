package com.lizarragabriel.retrofitcall.data.repository

import com.lizarragabriel.retrofitcall.data.local.room.dao.BeerDao
import com.lizarragabriel.retrofitcall.data.local.room.entity.FavoriteBeer
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val myDao: BeerDao) {

    suspend fun mAllFavorites(userid: Int) = myDao.mAllBeers(userid)

    suspend fun mAddFavorite(favoriteBeer: FavoriteBeer) = myDao.mAddFavorite(favoriteBeer)

    suspend fun mDeleteFavorite(favoriteBeer: FavoriteBeer) = myDao.mDeleteFavorite(favoriteBeer)

    suspend fun mUpdateFavorite(favoriteBeer: FavoriteBeer) = myDao.mUpdateFavorite(favoriteBeer)

}