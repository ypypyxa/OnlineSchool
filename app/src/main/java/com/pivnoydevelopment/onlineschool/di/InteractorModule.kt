package com.pivnoydevelopment.onlineschool.di

import com.pivnoydevelopment.onlineschool.common.domain.api.LoginInteractor
import com.pivnoydevelopment.onlineschool.common.domain.impl.LoginInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<LoginInteractor> {
        LoginInteractorImpl(get())
    }

}