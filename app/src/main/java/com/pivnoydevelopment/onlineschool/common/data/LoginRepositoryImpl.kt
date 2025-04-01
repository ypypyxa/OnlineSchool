package com.pivnoydevelopment.onlineschool.common.data

import com.pivnoydevelopment.onlineschool.common.data.storage.LocalStorage
import com.pivnoydevelopment.onlineschool.common.domain.api.LoginRepository

class LoginRepositoryImpl(private val localStorage: LocalStorage) : LoginRepository {

    override fun getSavedLoginEmail(): String? {
        return localStorage.getSavedLoginEmail()
    }

    override fun saveLoginEmail(email: String) {
        localStorage.saveLoginEmail(email)
    }
}