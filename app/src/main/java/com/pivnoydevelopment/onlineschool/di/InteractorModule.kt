package com.pivnoydevelopment.onlineschool.di

import com.pivnoydevelopment.onlineschool.common.domain.db.api.FavoritesCoursesInteractor
import com.pivnoydevelopment.onlineschool.common.domain.db.impl.FavoritesCoursesInteractorImpl
import com.pivnoydevelopment.onlineschool.common.domain.network.api.CoursesInteractor
import com.pivnoydevelopment.onlineschool.common.domain.storage.api.LoginInteractor
import com.pivnoydevelopment.onlineschool.common.domain.network.impl.CoursesInteractorImpl
import com.pivnoydevelopment.onlineschool.common.domain.storage.impl.LoginInteractorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<LoginInteractor> {
        LoginInteractorImpl(get())
    }

    single<CoursesInteractor> {
        CoursesInteractorImpl(get())
    }

    single<FavoritesCoursesInteractor> {
        FavoritesCoursesInteractorImpl(get())
    }

}