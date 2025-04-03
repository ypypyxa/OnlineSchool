package com.pivnoydevelopment.onlineschool.di

import com.pivnoydevelopment.common.data.db.impl.FavoritesCoursesRepositoryImpl
import com.pivnoydevelopment.common.data.network.impl.CoursesRepositoryImpl
import com.pivnoydevelopment.common.data.storage.LoginRepositoryImpl
import com.pivnoydevelopment.common.domain.db.api.FavoritesCoursesRepository
import com.pivnoydevelopment.common.domain.network.api.CoursesRepository
import com.pivnoydevelopment.common.domain.storage.api.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<LoginRepository> {
        LoginRepositoryImpl(get())
    }

    single<CoursesRepository> {
        CoursesRepositoryImpl(get(), get())
    }

    single<FavoritesCoursesRepository> {
        FavoritesCoursesRepositoryImpl(get(), get())
    }

}