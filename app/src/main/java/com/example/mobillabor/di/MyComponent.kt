package com.example.mobillabor.di

import com.example.mobillabor.ui.player.PlayerActivity
import com.example.mobillabor.ui.team.TeamActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MyModule::class])
interface MyComponent {
    fun inject(teamActivity: TeamActivity)
    fun inject(playerActivity: PlayerActivity)
}