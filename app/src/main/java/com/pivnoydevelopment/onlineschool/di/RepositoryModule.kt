package com.pivnoydevelopment.onlineschool.di

import com.pivnoydevelopment.onlineschool.common.data.CoursesRepositoryImpl
import com.pivnoydevelopment.onlineschool.common.data.LoginRepositoryImpl
import com.pivnoydevelopment.onlineschool.common.domain.api.CoursesRepository
import com.pivnoydevelopment.onlineschool.common.domain.api.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }

    single<CoursesRepository> {
        CoursesRepositoryImpl(get())
    }

}