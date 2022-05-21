package com.lizarragabriel.pruebaandroid.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface BeerApi {
    @GET("beers")
    suspend fun mAllBeers(): Response<List<Beer>>
}