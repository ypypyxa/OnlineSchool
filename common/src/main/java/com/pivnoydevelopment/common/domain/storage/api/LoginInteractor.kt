package com.pivnoydevelopment.common.domain.storage.api

interface LoginInteractor {
    fun getSavedLoginEmail(): String?
    fun saveLoginEmail(email: String)
}