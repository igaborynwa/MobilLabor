package com.example.mobillabor

val testInjector: TestComponent
    get(){
        return DaggerTestComponent.builder().testModule(TestModule()).build()
    }