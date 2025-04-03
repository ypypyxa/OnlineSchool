package com.pivnoydevelopment.onlineschool.di

import com.pivnoydevelopment.onlineschool.common.data.db.impl.FavoritesCoursesRepositoryImpl
import com.pivnoydevelopment.onlineschool.common.data.network.impl.CoursesRepositoryImpl
import com.pivnoydevelopment.onlineschool.common.data.storage.LoginRepositoryImpl
import com.pivnoydevelopment.onlineschool.common.domain.db.api.FavoritesCoursesRepository
import com.pivnoydevelopment.onlineschool.common.domain.network.api.CoursesRepository
import com.pivnoydevelopment.onlineschool.common.domain.storage.api.LoginRepository
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