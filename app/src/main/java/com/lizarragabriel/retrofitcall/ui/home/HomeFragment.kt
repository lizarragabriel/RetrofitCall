package com.lizarragabriel.retrofitcall.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.lizarragabriel.retrofitcall.R
import com.lizarragabriel.retrofitcall.databinding.FragmentHomeBinding
import com.lizarragabriel.retrofitcall.data.local.sharedpref.SharedPref
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val myModel: HomeViewModel by viewModels()

    private lateinit var myAdapter: BeerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            model = myModel
            lifecycleOwner = viewLifecycleOwner
        }
        myAdapter = BeerAdapter()
        binding.mRecyclerView.adapter = myAdapter

        // Clicks RecyclerView
        myAdapter.myCard = {
            SharedPref.beer = it
            view.findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
        }
        myAdapter.myHeart = { beer ->
            myModel.mFavoriteClick(beer)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.mLogOut -> {
                SharedPref.setSession(0)
                activity?.onBackPressed()
                false
            }
            R.id.mFavorita -> {
                view?.findNavController()?.navigate(R.id.action_homeFragment_to_favoriteFragment)
                false
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}