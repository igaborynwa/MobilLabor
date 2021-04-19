package com.example.mobillabor.test

import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.repository.network.FootballApi
import retrofit2.Call

class PlayerTest : FootballApi{
    override fun getTeamById(id: Int, xAuthToken: String): Call<Team> {
        TODO("Not yet implemented")
    }

    override fun getPlayersId(id: Int, xAuthToken: String): Call<Player> {
        TODO("Not yet implemented")
    }

    override fun putPlayersId(id: Int, xAuthToken: String, param: Player): Call<Player> {
        TODO("Not yet implemented")
    }

    override fun postPlayersId(id: Int, xAuthToken: String, param: Player): Call<Void> {
        TODO("Not yet implemented")
    }

    override fun deletePlayersId(id: Int, xAuthToken: String): Call<Void> {
        TODO("Not yet implemented")
    }
}