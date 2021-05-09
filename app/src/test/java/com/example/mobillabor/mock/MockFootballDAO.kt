package com.example.mobillabor.mock

import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.repository.database.FootballDAO

class MockFootballDAO: FootballDAO {
    private var teams =Team(64, "Liverpool FC","http://www.liverpoolfc.tv",
            "https://crests.football-data.org/64.svg", 1892, "Red / White", "Anfield")
    private var players = ArrayList<Player>()


    override fun getTeam(id: Int): Team {
        teams.squad = players
        return teams
    }

    override fun getPlayers(): List<Player> {
        return players
    }

    override fun getPlayer(id: Int): Player {
       return Player(3754, "Mohamed Salah","1992-06-15", "Egypt", "Egypt","Attacker")
    }

    override fun insertTeam(team: Team) {
        teams = team
    }

    override fun insertPlayer(player: Player) {
        players.add(player)
    }

    override fun deletePlayer(player: Player) {
        players.remove(player)
    }


}