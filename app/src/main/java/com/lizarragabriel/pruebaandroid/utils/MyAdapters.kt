package com.lizarragabriel.pruebaandroid.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.lizarragabriel.pruebaandroid.R
import com.lizarragabriel.pruebaandroid.data.remote.Beer
import com.lizarragabriel.pruebaandroid.data.local.room.entity.FavoriteBeer
import com.lizarragabriel.pruebaandroid.data.local.sharedpref.SharedPref
import com.lizarragabriel.pruebaandroid.ui.favorite.FavoriteAdapter
import com.lizarragabriel.pruebaandroid.ui.home.BeerAdapter
import com.squareup.picasso.Picasso

object MyAdapters {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {
        if(url != null) {
            Picasso.get().load(url).into(imageView)
        }
    }

    @BindingAdapter("loadBeers")
    @JvmStatic
    fun loadBeers(recyclerView: RecyclerView, beers: List<Beer>?) {
        val myAdapter = recyclerView.adapter
        if(myAdapter is BeerAdapter && beers != null) {
            myAdapter.setList(beers)
            println("habrá cambios")
        }
    }

    @BindingAdapter("loadFavorites")
    @JvmStatic
    fun loadFavorites(recyclerView: RecyclerView, favorites: List<FavoriteBeer>?) {
        val myAdapter = recyclerView.adapter
        if(myAdapter is FavoriteAdapter && favorites != null) {
            myAdapter.setList(favorites)
        }
    }

    @BindingAdapter("checkLayoutEmail")
    @JvmStatic
    fun checkLayoutEmail(layout: TextInputLayout, error: Boolean) {
        if(error) {
            val myContext = layout.context
            val myMessage = myContext.getString(R.string.empty_user)

            layout.isErrorEnabled = true
            layout.error = myMessage
        } else {
            layout.isErrorEnabled = false
            layout.error = null
        }
    }

    @BindingAdapter("checkLayoutPassword")
    @JvmStatic
    fun checkLayoutPassword(layout: TextInputLayout, error: Boolean) {
        if(error) {
            val myContext = layout.context
            val myMessage = myContext.getString(R.string.empty_password)
            layout.isErrorEnabled = true
            layout.error = myMessage
        } else {
            layout.isErrorEnabled = false
            layout.error = null
        }
    }

    @BindingAdapter("showArray")
    @JvmStatic
    fun showArray(textview: TextView, num: Int) {
        when(num) {
            0 -> {
                val list = SharedPref.beer.ingredients.malt
                var message = ""
                list.forEach {
                    message += "*${it.name} ${it.amount.value} ${it.amount.unit.substring(0, 1)}\n"
                }
                textview.text = message
            }
            1 -> {
                val list = SharedPref.beer.ingredients.hops
                var message = ""
                list.forEach {
                    message += "*${it.name} ${it.amount.value} ${it.amount.unit.substring(0, 1)}\n"
                }
                textview.text = message
            }
            else -> println("error")
        }

    }

    @BindingAdapter("myFavorite")
    @JvmStatic
    fun myFavorite(image: ImageView, favorite: Boolean?) {
        if(favorite != null) {
            if(favorite) {
                image.setImageResource(R.drawable.ic_favorite_24)
            } else {
                image.setImageResource(R.drawable.ic_nofavorite_24)
            }
        }
    }
}