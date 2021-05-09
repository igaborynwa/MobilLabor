package com.example.mobillabor

import com.example.mobillabor.di.MyComponent
import com.example.mobillabor.test.PlayerTest
import com.example.mobillabor.test.TeamTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TestModule::class])
interface TestComponent: MyComponent {
    fun inject(playerTest: PlayerTest)
    fun inject(teamTest: TeamTest)
}
