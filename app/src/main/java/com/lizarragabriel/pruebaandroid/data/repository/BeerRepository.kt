package com.lizarragabriel.pruebaandroid.data.repository

import com.lizarragabriel.pruebaandroid.data.remote.BeerApi
import javax.inject.Inject

class BeerRepository @Inject constructor(private val myApi: BeerApi)  {

    suspend fun mAllBeers() = myApi.mAllBeers()

}