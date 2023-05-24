package com.lizarragabriel.retrofitcall.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lizarragabriel.retrofitcall.databinding.FavoriteItemBinding
import com.lizarragabriel.retrofitcall.data.local.room.entity.FavoriteBeer

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {

    private var myList: List<FavoriteBeer> = emptyList()
    var myRating: (FavoriteBeer) -> Unit = {}

    fun setList(myList: List<FavoriteBeer>) {
        this.myList = myList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: FavoriteItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: FavoriteBeer) {
            binding.favorite = favorite
            binding.mRating.setOnRatingBarChangeListener { ratingBar, fl, b ->
                if(b) {
                    favorite.rating = ratingBar.progress
                    println("${ratingBar.progress}. $fl. $b")
                    myRating(favorite)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.MyViewHolder {
        return MyViewHolder(FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.MyViewHolder, position: Int) {
        holder.bind(myList[position])
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}