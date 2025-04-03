package com.pivnoydevelopment.onlineschool.di

import com.pivnoydevelopment.common.domain.db.api.FavoritesCoursesInteractor
import com.pivnoydevelopment.common.domain.db.impl.FavoritesCoursesInteractorImpl
import com.pivnoydevelopment.common.domain.network.api.CoursesInteractor
import com.pivnoydevelopment.common.domain.storage.api.LoginInteractor
import com.pivnoydevelopment.common.domain.network.impl.CoursesInteractorImpl
import com.pivnoydevelopment.common.domain.storage.impl.LoginInteractorImpl
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