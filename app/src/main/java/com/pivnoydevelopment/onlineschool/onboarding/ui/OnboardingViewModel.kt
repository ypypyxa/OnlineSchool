package com.pivnoydevelopment.onlineschool.onboarding.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pivnoydevelopment.onlineschool.common.domain.api.LoginInteractor
import com.pivnoydevelopment.onlineschool.onboarding.model.Destination

class OnboardingViewModel(private val loginInteractor: LoginInteractor) : ViewModel() {

    private val _navigateTo = MutableLiveData<Destination?>()
    val navigateTo: LiveData<Destination?> = _navigateTo

    init {
        val email = loginInteractor.getSavedLoginEmail()
        if (!email.isNullOrEmpty()) {
            _navigateTo.value = Destination.SEARCH
        }
    }
}