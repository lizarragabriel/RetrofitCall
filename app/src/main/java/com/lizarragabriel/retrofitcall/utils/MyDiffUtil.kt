package com.lizarragabriel.retrofitcall.utils

import androidx.recyclerview.widget.DiffUtil
import com.lizarragabriel.retrofitcall.data.remote.Beer


class MyDiffUtil(
    private val oldList: List<Beer>,
    private val newList: List<Beer>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when (oldList[oldItemPosition].favorite) {
            newList[newItemPosition].favorite -> {
                false
            }
            else -> true
        }
    }
}