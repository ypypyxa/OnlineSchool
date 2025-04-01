package com.pivnoydevelopment.onlineschool.di

import android.content.Context
import android.content.SharedPreferences
import com.pivnoydevelopment.onlineschool.common.data.storage.LocalStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<SharedPreferences> {
        androidContext().getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    single {
        LocalStorage(get())
    }
}