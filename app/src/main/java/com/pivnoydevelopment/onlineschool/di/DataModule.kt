package com.pivnoydevelopment.onlineschool.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.pivnoydevelopment.common.data.network.NetworkClient
import com.pivnoydevelopment.common.data.db.database.AppDatabase
import com.pivnoydevelopment.common.data.network.GoogleDiskApi
import com.pivnoydevelopment.common.data.network.impl.RetrofitNetworkClient
import com.pivnoydevelopment.common.data.storage.LocalStorage
import com.pivnoydevelopment.common.utils.DbConverter
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

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db").build()
    }

    factory { DbConverter() }

}