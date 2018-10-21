package com.example.jerry.mvvmpractice.di

import com.example.jerry.mvvmpractice.view.PaoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class  ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun paoActivity(): PaoActivity
}