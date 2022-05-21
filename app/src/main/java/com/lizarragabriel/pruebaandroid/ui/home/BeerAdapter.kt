package com.lizarragabriel.pruebaandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lizarragabriel.pruebaandroid.databinding.BeerItemBinding
import com.lizarragabriel.pruebaandroid.data.remote.Beer
import com.lizarragabriel.pruebaandroid.utils.MyDiffUtil

class BeerAdapter: RecyclerView.Adapter<BeerAdapter.MyViewHolder>() {
    private var myList: List<Beer> = emptyList()

    var myCard: (Beer) -> Unit = {}
    var myHeart: (Beer) -> Unit = {}

    fun setList(newList: List<Beer>) {
        //myList = newList
        val diffUtil = MyDiffUtil(myList, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        myList = newList
        diffResults.dispatchUpdatesTo(this)

    }

    inner class MyViewHolder(private val binding: BeerItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(beer: Beer) {
            binding.beer = beer
            binding.card.setOnClickListener {
                myCard(beer)
            }
            binding.myHeart.setOnClickListener {
                myHeart(beer)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(BeerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(myList[position])
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}