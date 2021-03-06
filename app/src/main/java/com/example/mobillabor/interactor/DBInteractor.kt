package com.example.mobillabor.interactor

import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.repository.database.FootballDAO
import javax.inject.Inject

class DBInteractor @Inject constructor(private var footballDAO: FootballDAO) {


    fun getPlayers() = footballDAO.getPlayers()

    fun getPlayer(id: Int) = footballDAO.getPlayer(id)


    fun getTeam(id: Int) = footballDAO.getTeam(id)

    fun insertPlayer(player: Player) {
        footballDAO.insertPlayer(player)
    }

    fun insertTeam(team: Team){
        footballDAO.insertTeam(team)
    }


    fun deletePlayer(player: Player){
        footballDAO.deletePlayer(player)
    }
}