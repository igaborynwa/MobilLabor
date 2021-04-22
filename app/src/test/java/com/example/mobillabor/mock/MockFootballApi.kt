package com.example.mobillabor.mock

import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.repository.network.FootballApi
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MockFootballApi : FootballApi {
    override fun getTeamById(id: Int, xAuthToken: String): Call<Team> {

        val team = Team(64, "Liverpool FC","http://www.liverpoolfc.tv",
            "https://crests.football-data.org/64.svg", 1892, "Red / White", "Anfield")

        val call = object : Call<Team> {
            @Throws(IOException::class)
            override fun execute(): Response<Team> {
                return Response.success(team)
            }
            override fun enqueue(callback: Callback<Team>) { }
            override fun isExecuted(): Boolean {
                return false
            }
            override fun cancel() { }
            override fun isCanceled(): Boolean {
                return false
            }
            override fun clone(): Call<Team> {
                return this
            }
            override fun request(): Request? {
                return null
            }
        }
        return call

    }

    override fun getPlayersId(id: Int, xAuthToken: String): Call<Player> {
        TODO("Not yet implemented")
    }

    override fun putPlayersId(id: Int, xAuthToken: String, param: Player): Call<Player> {
        TODO("Not yet implemented")
    }

    override fun postPlayersId(xAuthToken: String, param: Player): Call<Int> {
        TODO("Not yet implemented")
    }

    override fun deletePlayersId(id: Int, xAuthToken: String): Call<Void> {
        TODO("Not yet implemented")
    }
}