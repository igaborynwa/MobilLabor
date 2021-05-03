package com.example.mobillabor.di

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.mobillabor.interactor.DBInteractor
import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.model.Player
import com.example.mobillabor.repository.database.AppDatabase
import com.example.mobillabor.repository.database.FootballDAO
import com.example.mobillabor.repository.network.FootballApi
import com.example.mobillabor.ui.player.PlayerPresenter
import com.example.mobillabor.ui.team.TeamPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MyModule(private var context: Context) {
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
    fun providesFootballDao(): FootballDAO{
        return AppDatabase.getInstance(context).footballDao()
    }

    @Provides
    @Singleton
    fun providesDBInteractor(footballDAO: FootballDAO) = DBInteractor(footballDAO)

    @Provides
    @Singleton
    fun providesNetworkInteractor(footballApi: FootballApi) = NetworkInteractor(footballApi)

    @Provides
    @Singleton
    fun providesTeamPresenter(networkInteractor: NetworkInteractor, dbInteractor: DBInteractor) = TeamPresenter(networkInteractor, dbInteractor)

    @Provides
    @Singleton
    fun providesPlayerPresenter(networkInteractor: NetworkInteractor, dbInteractor: DBInteractor) = PlayerPresenter(networkInteractor, dbInteractor)


}