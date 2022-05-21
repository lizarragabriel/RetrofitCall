package com.lizarragabriel.pruebaandroid.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizarragabriel.pruebaandroid.R
import com.lizarragabriel.pruebaandroid.data.repository.UserRepository
import com.lizarragabriel.pruebaandroid.data.local.room.entity.User
import com.lizarragabriel.pruebaandroid.utils.MyEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private lateinit var user: String
    private lateinit var password: String

    // Verificar usuario
    private var _errorEmail = MutableLiveData<Boolean>()
    val errorEmail: LiveData<Boolean> get() = _errorEmail

    // Verificar contraseña
    private var _errorPassword = MutableLiveData<Boolean>()
    val errorPassword: LiveData<Boolean> get() = _errorPassword

    // Mensaje
    private var _toast = MutableLiveData<MyEvent<Int>>()
    val toast: LiveData<MyEvent<Int>> get() = _toast

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

    // Activar errores en TextFields
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

    // Mostrar mensaje
    private fun mToast(message: Int) {
        _toast.value = MyEvent(message)
    }

    private suspend fun mAddUser() {
        val newUser = User(null, user, password)
        repository.mAddUser(newUser)
    }
}