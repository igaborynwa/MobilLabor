package com.example.mobillabor.interactor

import android.os.Handler
import android.util.Log
import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.repository.network.FootballApi
import retrofit2.Call
import javax.inject.Inject

class NetworkInteractor @Inject constructor(private var api: FootballApi){
    private val token = "523d71ebf3984ab0ae29ead39e61497f"


    private fun <T> runCallOnBackgroundThread(
        call: Call<T>,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val handler = Handler()
        Thread {
            try {
                val response = call.execute().body()!!
                handler.post {
                    onSuccess(response)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                handler.post { onError(e) }
            }
        }.start()
    }

    fun getTeam(id: Int, onSuccess:(Team)->Unit, onError: (Throwable)->Unit){
        val request = api.getTeamById(id, token)
        runCallOnBackgroundThread(request, onSuccess, onError)
    }

    fun getPlayer(id: Int, onSuccess:(Player)->Unit, onError: (Throwable)->Unit){
        val request = api.getPlayersId(id, token)
        runCallOnBackgroundThread(request, onSuccess, onError)
    }

    fun modifyPlayer(player: Player, onSuccess:(Player)->Unit, onError: (Throwable)->Unit){
        onSuccess(player)
    }

    fun createPlayer(player: Player, onSuccess:(Int)->Unit, onError: (Throwable)->Unit ){

        onSuccess(player.id)
    }

    fun deletePlayer(player: Player, onSuccess:(String)->Unit, onError: (Throwable)->Unit){
        onSuccess(player.name)
    }

}