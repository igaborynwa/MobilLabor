package com.example.mobillabor.ui.team

import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.model.Team
import com.example.mobillabor.ui.Presenter
import javax.inject.Inject

class TeamPresenter @Inject constructor(private val networkInteractor: NetworkInteractor): Presenter<TeamScreen?>() {


    fun getTeam(){
        networkInteractor.getTeam(64, onSuccess = this::onGetTeamSuccess, this::onError)
    }

    private fun onGetTeamSuccess(team: Team) {
        screen?.showTeam(team)
    }

    private fun onError(e: Throwable){
        screen?.showNetworkError(e)
    }
}