package com.example.mobillabor.di

import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.repository.network.FootballApi
import com.example.mobillabor.ui.team.TeamPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MyModule {
    @Provides
    @Singleton
    fun providesFootballApi(): FootballApi{
        val retrofit= Retrofit.Builder()
            .baseUrl(FootballApi.ENDPOINT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(FootballApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNetworkInteractor(footballApi: FootballApi) = NetworkInteractor(footballApi)

    @Provides
    @Singleton
    fun providesTeamPresenter(networkInteractor: NetworkInteractor) = TeamPresenter(networkInteractor)
}