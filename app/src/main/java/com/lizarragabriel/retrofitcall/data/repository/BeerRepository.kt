package com.lizarragabriel.retrofitcall.data.repository

import com.lizarragabriel.retrofitcall.data.remote.BeerApi
import javax.inject.Inject

class BeerRepository @Inject constructor(private val myApi: BeerApi)  {

    suspend fun mAllBeers() = myApi.mAllBeers()

}