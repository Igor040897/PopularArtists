package com.example.popularartists

import android.app.Application
import com.example.popularartists.di.component.ApplicationComponent
import com.example.popularartists.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerApplicationComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}