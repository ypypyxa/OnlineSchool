package com.pivnoydevelopment.onlineschool.common.data.storage

import com.pivnoydevelopment.onlineschool.common.domain.storage.api.LoginRepository

class LoginRepositoryImpl(private val localStorage: LocalStorage) : LoginRepository {

    override fun getSavedLoginEmail(): String? {
        return localStorage.getSavedLoginEmail()
    }

    override fun saveLoginEmail(email: String) {
        localStorage.saveLoginEmail(email)
    }
}