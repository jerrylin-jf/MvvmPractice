package com.example.jerry.mvvmpractice.di

import android.app.Application
import android.content.Context
import com.example.jerry.mvvmpractice.model.local.AppDatabase
import com.example.jerry.mvvmpractice.model.local.dao.PaoDao
import com.example.jerry.mvvmpractice.model.remote.PaoService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRemoteClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://api.jcodecraeer.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    .build()
            )
            .build()

    @Provides
    @Singleton
    fun providePaoService(client: Retrofit): PaoService = client.create(PaoService::class.java)

    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): AppDatabase = AppDatabase.getInstance(application)

    @Provides
    @Singleton
    fun providePaoDao(database: AppDatabase): PaoDao = database.paoDao()
}