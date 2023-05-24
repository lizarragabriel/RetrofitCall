package com.lizarragabriel.retrofitcall.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizarragabriel.retrofitcall.R
import com.lizarragabriel.retrofitcall.data.repository.UserRepository
import com.lizarragabriel.retrofitcall.data.local.sharedpref.SharedPref
import com.lizarragabriel.retrofitcall.utils.MyEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private lateinit var username: String
    private lateinit var password: String

    // Verify user
    private var _errorEmail = MutableLiveData<Boolean>()
    val errorEmail: LiveData<Boolean> get() = _errorEmail

    // Verify password
    private var _errorPassword = MutableLiveData<Boolean>()
    val errorPassword: LiveData<Boolean> get() = _errorPassword

    // Home Navigation
    private var _navigate = MutableLiveData<MyEvent<Int>>()
    val navigate: LiveData<MyEvent<Int>> get() = _navigate

    // Status message
    private var _toast = MutableLiveData<MyEvent<Int>>()
    val toast: LiveData<MyEvent<Int>> get() = _toast

    // Verify empty fields
    fun mLoginButton(username: String, password: String) {
        myError(_errorEmail, false)
        myError(_errorPassword, false)

        if(username.isEmpty()) {
            myError(_errorEmail, true)
        }
        if(password.isEmpty()) {
            myError(_errorPassword, true)
        }

        if(username.isEmpty() || password.isEmpty()) {
            return
        }

        this.username = username
        this.password = password
        mLogin()
    }

    // Login
    private fun mLogin() {
        viewModelScope.launch {
            val find = repository.mFindOne(username)
            if(find == null) {
                mToast(R.string.wrong_credential)
            } else {
                if(password == find.password) {
                    SharedPref.setSession(find.id!!)
                    mNavigate()
                }
            }
        }
    }

    // Show message
    private fun mToast(message: Int) {
        _toast.value = MyEvent(message)
    }

    // Show errors in TextLayouts
    private fun myError(data: MutableLiveData<Boolean>, error: Boolean) {
        data.postValue(error)
    }

    // Navigate home
    private fun mNavigate() {
        _navigate.value = MyEvent(R.id.action_loginFragment_to_homeFragment)
    }
}