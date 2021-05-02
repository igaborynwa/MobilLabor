package com.example.mobillabor.ui.team

import android.util.Log
import com.example.mobillabor.interactor.DBInteractor
import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.ui.Presenter
import javax.inject.Inject

class TeamPresenter @Inject constructor(private val networkInteractor: NetworkInteractor,
                                        private val dbInteractor: DBInteractor): Presenter<TeamScreen?>() {
    fun getTeam(){
        networkInteractor.getTeam(64, onSuccess = this::onGetTeamSuccess, this::onError)
    }

    private fun onGetTeamSuccess(team: Team) {
        addTeamToDB(team)
        screen?.showTeam(team)
    }

    private fun addTeamToDB(team: Team){

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
        Log.d("Player", "added")
    }

    private fun onPlayerAddedSuccess(id: Int){
        Log.d("created", "player")
        screen?.showPlayerAdded(id)
    }

    fun modifyPlayer(player: Player){
        networkInteractor.modifyPlayer(player, onSuccess = this::onPlayerModified, onError = this::onError)
    }

    private fun onPlayerModified(player: Player) {
        screen?.showPlayerModified(player)
    }
}