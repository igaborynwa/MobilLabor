package com.example.mobillabor

import android.content.Context
import com.example.mobillabor.interactor.DBInteractor
import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.mock.MockFootballApi
import com.example.mobillabor.mock.MockFootballDAO
import com.example.mobillabor.repository.database.FootballDAO
import com.example.mobillabor.repository.network.FootballApi
import com.example.mobillabor.ui.player.PlayerPresenter
import com.example.mobillabor.utils.UiExecutor
import dagger.Module
import dagger.Provides
import org.junit.runner.manipulation.Ordering
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class TestModule(context: Context) {
    @Provides
    @Singleton
    fun provideNetworkExecutor(): Executor = UiExecutor()

    @Provides
    @Singleton
    fun providesFootballApi(): FootballApi = MockFootballApi()

    @Provides
    @Singleton
    fun providesFootballDAO(): FootballDAO = MockFootballDAO()

    @Provides
    @Singleton
    fun providesNetworkInteractor(footballApi: FootballApi): NetworkInteractor = NetworkInteractor(footballApi)

    @Provides
    @Singleton
    fun providesDBInteractor(footballDAO: FootballDAO): DBInteractor = DBInteractor(footballDAO)

    @Provides
    @Singleton
    fun providesPlayerPresenter(networkInteractor: NetworkInteractor, dbInteractor: DBInteractor): PlayerPresenter = PlayerPresenter(networkInteractor,dbInteractor)
}