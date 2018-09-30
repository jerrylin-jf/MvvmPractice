package com.example.jerry.mvvmpractice

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class MvvmPracticeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        Timber.tag(applicationInfo.name)
    }
}