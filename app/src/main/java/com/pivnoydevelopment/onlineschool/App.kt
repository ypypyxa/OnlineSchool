package com.pivnoydevelopment.onlineschool

import android.app.Application
import com.pivnoydevelopment.onlineschool.di.dataModule
import com.pivnoydevelopment.onlineschool.di.interactorModule
import com.pivnoydevelopment.onlineschool.di.repositoryModule
import com.pivnoydevelopment.onlineschool.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, interactorModule, repositoryModule, viewModelModule)
        }
    }

}