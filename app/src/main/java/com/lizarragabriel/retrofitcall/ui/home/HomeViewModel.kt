package com.lizarragabriel.retrofitcall.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizarragabriel.retrofitcall.data.repository.BeerRepository
import com.lizarragabriel.retrofitcall.data.repository.FavoriteRepository
import com.lizarragabriel.retrofitcall.data.remote.Beer
import com.lizarragabriel.retrofitcall.data.local.room.entity.FavoriteBeer
import com.lizarragabriel.retrofitcall.data.local.sharedpref.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val beerRepository: BeerRepository,
    private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    val beers: LiveData<List<Beer>> get() = _beers
    private val _beers: MutableLiveData<List<Beer>> by lazy {
        MutableLiveData<List<Beer>>().also {
            viewModelScope.launch(Dispatchers.IO) {
                _loading.postValue(true)
                delay(500L)
                try {
                    val userid = SharedPref.getSession()
                    val favorites = favoriteRepository.mAllFavorites(userid)
                    val response = beerRepository.mAllBeers()
                    if(response.isSuccessful) {
                        val list = response.body()
                        favorites.forEach { beer ->
                            list!![beer.id - 1].favorite = true
                        }
                        it.postValue(list)
                    } else {
                        it.postValue(null)
                    }
                } catch (e: Exception) {
                    it.postValue(null)
                } finally {
                    _loading.postValue(false)
                }
            }
        }
    }

    fun mFavoriteClick(beer: Beer) {
        viewModelScope.launch {
            try {
                val userid = SharedPref.getSession()
                val favorite = FavoriteBeer(
                    beer.id,
                    userid,
                    beer.name,
                    beer.tagline,
                    beer.description,
                    beer.image_url
                )
                val list = beers.value
                list!![beer.id - 1].favorite = !beer.favorite
                _beers.postValue(list)

                if(beer.favorite) {
                    mAddFavorite(favorite)
                } else {
                    mDeleteFavorite(favorite)
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    suspend fun mAddFavorite(favorite: FavoriteBeer) {
        favoriteRepository.mAddFavorite(favorite)
    }

    suspend fun mDeleteFavorite(favorite: FavoriteBeer) {
        favoriteRepository.mDeleteFavorite(favorite)
    }
}