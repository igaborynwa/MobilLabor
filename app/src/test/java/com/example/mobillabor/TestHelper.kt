package com.example.mobillabor

import androidx.test.core.app.ApplicationProvider
import com.example.mobillabor.di.MainApplication

val testInjector: TestComponent
    get(){
        val application = ApplicationProvider.getApplicationContext() as MainApplication
        val component = DaggerTestComponent.builder().testModule(TestModule(application.applicationContext)).build()
        application.injector = component
        return component
    }