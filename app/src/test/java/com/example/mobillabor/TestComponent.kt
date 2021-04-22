package com.example.mobillabor

import com.example.mobillabor.test.PlayerTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class])
interface TestComponent {
    fun inject(playerTest: PlayerTest)
}
