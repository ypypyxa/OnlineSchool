package com.pivnoydevelopment.onlineschool.common.domain.api

interface LoginRepository {
    fun getSavedLoginEmail(): String?
    fun saveLoginEmail(email: String)
}