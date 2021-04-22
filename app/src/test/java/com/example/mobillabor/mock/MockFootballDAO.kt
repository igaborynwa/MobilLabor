package com.example.mobillabor.mock

import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.repository.database.FootballDAO

class MockFootballDAO: FootballDAO {
    private var teams = ArrayList<Team>()
    private var players = ArrayList<Player>()

    override fun getTeams(): List<Team> {
        return teams
    }

    override fun getPlayers(): List<Player> {
        return players
    }

    override fun getPlayer(id: Int): Player {
        for(p in players){
            if (p.id == id) return p
        }
        return players[0]
    }

    override fun insertTeam(team: Team) {
        teams.add(team)
    }

    override fun insertPlayer(player: Player) {
        players.add(player)
    }

    override fun deletePlayer(player: Player) {
        players.remove(player)
    }

    override fun deleteTeam(team: Team) {
        teams.remove(team)
    }
}