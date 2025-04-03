package com.pivnoydevelopment.common.domain.storage.impl

import com.pivnoydevelopment.common.domain.storage.api.LoginInteractor
import com.pivnoydevelopment.common.domain.storage.api.LoginRepository

class LoginInteractorImpl(private val repository: LoginRepository) : LoginInteractor {

    override fun getSavedLoginEmail(): String? {
        return repository.getSavedLoginEmail()
    }

    override fun saveLoginEmail(email: String) {
        repository.saveLoginEmail(email)
    }
}