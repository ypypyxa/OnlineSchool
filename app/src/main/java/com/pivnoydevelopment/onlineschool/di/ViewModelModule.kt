package com.pivnoydevelopment.onlineschool.di

import com.pivnoydevelopment.onlineschool.authorization.ui.AuthViewModel
import com.pivnoydevelopment.onlineschool.onboarding.ui.OnboardingViewModel
import com.pivnoydevelopment.onlineschool.courses.ui.CoursesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        AuthViewModel(get())
    }

    viewModel {
        OnboardingViewModel(get())
    }

    viewModel {
        CoursesViewModel(get())
    }
}