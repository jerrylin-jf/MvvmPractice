package com.example.jerry.mvvmpractice.di.component

import com.example.jerry.mvvmpractice.di.module.AppModule
import com.example.jerry.mvvmpractice.view.PaoActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(activity: PaoActivity)
}