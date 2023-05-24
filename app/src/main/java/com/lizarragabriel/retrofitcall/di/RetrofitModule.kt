package com.lizarragabriel.retrofitcall.di

import com.lizarragabriel.retrofitcall.data.remote.BeerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val BEER_API = "https://api.punkapi.com/v2/"

    @Singleton
    @Provides
    fun mProvideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BEER_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun mProvidesApi(retrofit: Retrofit): BeerApi {
        return retrofit.create(BeerApi::class.java)
    }
}