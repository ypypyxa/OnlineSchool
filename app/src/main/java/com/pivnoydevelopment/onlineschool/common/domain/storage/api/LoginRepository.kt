package com.pivnoydevelopment.onlineschool.common.domain.storage.api

interface LoginRepository {
    fun getSavedLoginEmail(): String?
    fun saveLoginEmail(email: String)
}