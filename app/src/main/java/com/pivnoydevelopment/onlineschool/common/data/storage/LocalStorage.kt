package com.pivnoydevelopment.onlineschool.common.data.storage

import android.content.SharedPreferences
import androidx.core.content.edit

class LocalStorage(private val sharedPreferences: SharedPreferences) {

    private companion object {
        const val EMAIL_KEY = "Email"
    }

    fun getSavedLoginEmail(): String? {
        return sharedPreferences.getString(EMAIL_KEY, "")
    }

    fun saveLoginEmail(email: String) {
        sharedPreferences.edit() { putString(EMAIL_KEY, email) }
    }
}