package com.pivnoydevelopment.common.data.storage

import com.pivnoydevelopment.common.domain.storage.api.LoginRepository

class LoginRepositoryImpl(private val localStorage: LocalStorage) : LoginRepository {

    override fun getSavedLoginEmail(): String? {
        return localStorage.getSavedLoginEmail()
    }

    override fun saveLoginEmail(email: String) {
        localStorage.saveLoginEmail(email)
    }
}