package com.lizarragabriel.pruebaandroid.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lizarragabriel.pruebaandroid.data.remote.Beer
import com.lizarragabriel.pruebaandroid.data.local.sharedpref.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    val beer: LiveData<Beer> get() = _beer
    private val _beer: MutableLiveData<Beer> by lazy {
        MutableLiveData<Beer>().also {
            it.postValue(SharedPref.beer)
        }
    }
}