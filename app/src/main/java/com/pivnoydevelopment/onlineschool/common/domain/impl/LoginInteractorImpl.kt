package com.pivnoydevelopment.onlineschool.common.domain.impl

import com.pivnoydevelopment.onlineschool.common.domain.api.LoginInteractor
import com.pivnoydevelopment.onlineschool.common.domain.api.LoginRepository

class LoginInteractorImpl(private val repository: LoginRepository) : LoginInteractor {

    override fun getSavedLoginEmail(): String? {
        return repository.getSavedLoginEmail()
    }

    override fun saveLoginEmail(email: String) {
        repository.saveLoginEmail(email)
    }
}