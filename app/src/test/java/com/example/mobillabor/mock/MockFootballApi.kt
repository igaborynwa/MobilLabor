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
        val squad = ArrayList<Player>()
        squad.add(Player(3754, "Mohamed Salah","1992-06-15", "Egypt", "Egypt","Attacker"))
        team.squad=squad

        return object : Call<Team> {
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

    }

    override fun getPlayersId(id: Int, xAuthToken: String): Call<Player> {
        val player = Player(3754, "Mohamed Salah","1992-06-15", "Egypt", "Egypt","Attacker")
        return object : Call<Player> {
            @Throws(IOException::class)
            override fun execute(): Response<Player> {
                return Response.success(player)
            }
            override fun enqueue(callback: Callback<Player>) { }
            override fun isExecuted(): Boolean {
                return false
            }
            override fun cancel() { }
            override fun isCanceled(): Boolean {
                return false
            }
            override fun clone(): Call<Player> {
                return this
            }
            override fun request(): Request? {
                return null
            }
        }
    }

    override fun putPlayersId(id: Int, xAuthToken: String, param: Player): Call<Player> {
        return object : Call<Player> {
            @Throws(IOException::class)
            override fun execute(): Response<Player> {
                return Response.success(param)
            }
            override fun enqueue(callback: Callback<Player>) { }
            override fun isExecuted(): Boolean {
                return false
            }
            override fun cancel() { }
            override fun isCanceled(): Boolean {
                return false
            }
            override fun clone(): Call<Player> {
                return this
            }
            override fun request(): Request? {
                return null
            }
        }
    }

    override fun postPlayersId(xAuthToken: String, param: Player): Call<Int> {
        return object : Call<Int> {
            @Throws(IOException::class)
            override fun execute(): Response<Int> {
                return Response.success(param.id)
            }
            override fun enqueue(callback: Callback<Int>) { }
            override fun isExecuted(): Boolean {
                return false
            }
            override fun cancel() { }
            override fun isCanceled(): Boolean {
                return false
            }
            override fun clone(): Call<Int> {
                return this
            }
            override fun request(): Request? {
                return null
            }
        }
    }

    override fun deletePlayersId(id: Int, xAuthToken: String): Call<String> {

        return object : Call<String> {
            @Throws(IOException::class)
            override fun execute(): Response<String> {
                return Response.success("Mohamed Salah")
            }
            override fun enqueue(callback: Callback<String>) { }
            override fun isExecuted(): Boolean {
                return false
            }
            override fun cancel() { }
            override fun isCanceled(): Boolean {
                return false
            }
            override fun clone(): Call<String> {
                return this
            }
            override fun request(): Request? {
                return null
            }
        }
    }
}