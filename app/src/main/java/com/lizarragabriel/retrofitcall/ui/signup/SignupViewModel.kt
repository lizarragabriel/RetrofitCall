package com.lizarragabriel.retrofitcall.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizarragabriel.retrofitcall.R
import com.lizarragabriel.retrofitcall.data.repository.UserRepository
import com.lizarragabriel.retrofitcall.data.local.room.entity.User
import com.lizarragabriel.retrofitcall.utils.MyEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private lateinit var user: String
    private lateinit var password: String

    // Verify email
    private var _errorEmail = MutableLiveData<Boolean>()
    val errorEmail: LiveData<Boolean> get() = _errorEmail

    // Verify password
    private var _errorPassword = MutableLiveData<Boolean>()
    val errorPassword: LiveData<Boolean> get() = _errorPassword

    // Mensaje
    private var _toast = MutableLiveData<MyEvent<Int>>()
    val toast: LiveData<MyEvent<Int>> get() = _toast

    //  Sign up button
    fun mSignupButton(user: String, password: String) {
        myError(_errorEmail, false)
        myError(_errorPassword, false)

        if(user.isEmpty()) {
            myError(_errorEmail, true)
        }
        if(password.isEmpty()) {
            myError(_errorPassword, true)
        }

        if(user.isEmpty() || password.isEmpty()) {
            return
        }

        this.user = user
        this.password = password
        mSignup()
    }

    // Show error in TextLayouts
    private fun myError(data: MutableLiveData<Boolean>, error: Boolean) {
        data.postValue(error)
    }

    private fun mSignup() {
        viewModelScope.launch {
            val find = repository.mFindOne(user)
            if(find == null) {
                mToast(R.string.user_addedd)
                mAddUser()
            } else {
                mToast(R.string.alredy_exists)
            }
        }
    }

    // Show message
    private fun mToast(message: Int) {
        _toast.value = MyEvent(message)
    }

    // Add new user
    private suspend fun mAddUser() {
        val newUser = User(null, user, password)
        repository.mAddUser(newUser)
    }
}