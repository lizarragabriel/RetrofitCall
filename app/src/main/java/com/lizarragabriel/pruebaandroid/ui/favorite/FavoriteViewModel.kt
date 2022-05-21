package com.lizarragabriel.pruebaandroid.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizarragabriel.pruebaandroid.data.repository.FavoriteRepository
import com.lizarragabriel.pruebaandroid.data.local.room.entity.FavoriteBeer
import com.lizarragabriel.pruebaandroid.data.local.sharedpref.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    val favorites: LiveData<List<FavoriteBeer>> get() = _favorites
    private val _favorites: MutableLiveData<List<FavoriteBeer>> by lazy {
        MutableLiveData<List<FavoriteBeer>>().also {
            viewModelScope.launch {
                val userid = SharedPref.getSession()
                val response = favoriteRepository.mAllFavorites(userid)
                it.postValue(response)
            }
        }
    }

    fun mUpdateFavorite(favoriteBeer: FavoriteBeer) {
        viewModelScope.launch {
            favoriteRepository.mUpdateFavorite(favoriteBeer)
        }
    }
}