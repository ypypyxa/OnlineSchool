package com.pivnoydevelopment.onlineschool.di

import android.content.Context
import android.content.SharedPreferences
import com.pivnoydevelopment.onlineschool.common.NetworkClient
import com.pivnoydevelopment.onlineschool.common.data.network.GoogleDiskApi
import com.pivnoydevelopment.onlineschool.common.data.network.RetrofitNetworkClient
import com.pivnoydevelopment.onlineschool.common.data.storage.LocalStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<SharedPreferences> {
        androidContext().getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    single {
        LocalStorage(get())
    }

    single<GoogleDiskApi> {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleDiskApi::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }

}