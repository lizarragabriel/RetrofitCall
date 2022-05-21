package com.lizarragabriel.pruebaandroid.ui.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.lizarragabriel.pruebaandroid.R
import com.lizarragabriel.pruebaandroid.databinding.FragmentLoginBinding
import com.lizarragabriel.pruebaandroid.data.local.sharedpref.SharedPref
import com.lizarragabriel.pruebaandroid.utils.extensions.myNavigation
import com.lizarragabriel.pruebaandroid.utils.extensions.setUpNavigation
import com.lizarragabriel.pruebaandroid.utils.extensions.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val myModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            model = myModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@LoginFragment
        }
        // Si hay una sesión guardada, navegará directamente al Home Fragment
        if(SharedPref.getSession() != 0) {
            view.myNavigation(R.id.action_loginFragment_to_homeFragment)
        }

        // navigate
        view.setUpNavigation(myModel.navigate, viewLifecycleOwner)

        // toast
        view.showToast(myModel.toast, viewLifecycleOwner)
    }

    fun mNavigateSign() {
        view?.myNavigation(R.id.action_loginFragment_to_signUpFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}