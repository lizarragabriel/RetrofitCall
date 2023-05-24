package com.lizarragabriel.retrofitcall.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lizarragabriel.retrofitcall.R
import com.lizarragabriel.retrofitcall.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val myModel: FavoriteViewModel by viewModels()

    private lateinit var myAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            model = myModel
            lifecycleOwner = viewLifecycleOwner
        }

        myAdapter = FavoriteAdapter()
        binding.mRecycler.adapter = myAdapter

        myAdapter.myRating = {
            myModel.mUpdateFavorite(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}