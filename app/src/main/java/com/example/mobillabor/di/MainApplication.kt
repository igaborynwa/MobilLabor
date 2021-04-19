package com.example.mobillabor.di

import android.app.Application

class MainApplication: Application() {
    lateinit var injector: MyComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerMyComponent.builder().myModule(MyModule()).build()
    }
}