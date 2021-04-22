package com.example.mobillabor

import com.example.mobillabor.mock.MockFootballApi
import com.example.mobillabor.mock.MockFootballDAO
import com.example.mobillabor.repository.database.FootballDAO
import com.example.mobillabor.repository.network.FootballApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestModule {
    @Provides
    @Singleton
    fun providesFootballApi(): FootballApi = MockFootballApi()

    @Provides
    @Singleton
    fun providesFootballDAO(): FootballDAO = MockFootballDAO()
}