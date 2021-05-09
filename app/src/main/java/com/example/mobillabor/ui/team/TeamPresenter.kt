package com.example.mobillabor.ui.team

import com.example.mobillabor.interactor.DBInteractor
import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.ui.Presenter
import javax.inject.Inject

class TeamPresenter @Inject constructor(private val networkInteractor: NetworkInteractor,
                                        private val dbInteractor: DBInteractor): Presenter<TeamScreen?>() {
    fun getTeam(con: Boolean){
        if(con)
            Thread{networkInteractor.getTeam(64, onSuccess = this::onGetTeamSuccess, this::onError)}.start()
        else getTeamFromDb()

    }

    private fun getTeamFromDb(){
        Thread{
            val team = dbInteractor.getTeam(64)
            val squad = dbInteractor.getPlayers()
            for(p in squad){
                team!!.squad.add(p)
            }
           onGetTeamSuccess(team!!)
        }.start()
    }

     private fun onGetTeamSuccess(team: Team) {
        addTeamToDB(team)
        screen?.showTeam(team)
    }

    private fun addTeamToDB(team: Team){
        Thread{
            val tmp = dbInteractor.getTeam(team.id!!)
            if(tmp==null){
                dbInteractor.insertTeam(team)
            }
            for(player in team.squad){
                if(dbInteractor.getPlayer(player.id)==null){
                    dbInteractor.insertPlayer(player)
                }
            }
        }.start()
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

    fun modifyPlayer(player: Player){
        networkInteractor.modifyPlayer(player, onSuccess = this::onPlayerModified, onError = this::onError)
    }

    private fun onPlayerModified(player: Player) {
        screen?.showPlayerModified(player)
    }
}