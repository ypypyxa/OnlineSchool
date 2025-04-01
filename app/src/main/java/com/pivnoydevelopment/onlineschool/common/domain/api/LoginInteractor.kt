package com.pivnoydevelopment.onlineschool.common.domain.api

interface LoginInteractor {
    fun getSavedLoginEmail(): String?
    fun saveLoginEmail(email: String)
}