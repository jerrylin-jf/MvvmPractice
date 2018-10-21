package com.example.jerry.mvvmpractice.di

import android.app.Application
import com.example.jerry.mvvmpractice.MvvmPracticeApplication
import com.example.jerry.mvvmpractice.view.PaoActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MvvmPracticeApplication)
}

//interface AppComponent : AndroidInjector<MvvmPracticeApplication> {
//    @Component.Builder
//    abstract class Builder : AndroidInjector.Builder<MvvmPracticeApplication>()
//
//    fun inject(activity: PaoActivity)
//}