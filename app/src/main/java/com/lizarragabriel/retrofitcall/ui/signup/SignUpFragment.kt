package com.lizarragabriel.retrofitcall.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lizarragabriel.retrofitcall.R
import com.lizarragabriel.retrofitcall.databinding.FragmentSignUpBinding
import com.lizarragabriel.retrofitcall.utils.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val myModel: SignupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            model = myModel
            lifecycleOwner = viewLifecycleOwner
        }
        view.showToast(myModel.toast, viewLifecycleOwner)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}