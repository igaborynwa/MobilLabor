package com.example.mobillabor.ui.team

import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.model.Player
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

    fun deletePlayer(player: Player){
        networkInteractor.deletePlayer(player, onSuccess = this::onDeleteSuccess, onError = this::onError)
    }

    private fun onDeleteSuccess(name: String){
        screen?.showDeleteSuccess(name)
    }

    fun addPlayer(player: Player){
        networkInteractor.createPlayer(player, onSuccess = this::onPlayerAddedSuccess, onError = this::onError)
    }

    private fun onPlayerAddedSuccess(id: Int){
        screen?.showPlayerAdded(id)
    }
}