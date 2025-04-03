package com.pivnoydevelopment.onlineschool.authorization.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pivnoydevelopment.onlineschool.common.domain.storage.api.LoginInteractor

class AuthViewModel(private val loginInteractor: LoginInteractor) : ViewModel() {

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val isLoginEnabled = MediatorLiveData<Boolean>().apply {
        addSource(_email) { validate() }
        addSource(_password) { validate() }
    }

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    fun saveEmail() {
        loginInteractor.saveLoginEmail(_email.value.orEmpty())
    }

    private fun validate() {
        val email = _email.value.orEmpty()
        val password = _password.value.orEmpty()
        isLoginEnabled.value = email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) &&
                password.isNotEmpty()
    }
}