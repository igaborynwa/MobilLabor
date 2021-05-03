package com.example.mobillabor.ui.player

import android.util.Log
import com.example.mobillabor.interactor.DBInteractor
import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.model.Player
import com.example.mobillabor.ui.Presenter
import javax.inject.Inject

class PlayerPresenter @Inject constructor(private val networkInteractor: NetworkInteractor, private val dbInteractor: DBInteractor): Presenter<PlayerScreen?>() {

    fun getPlayer(id: Int, con: Boolean){
        if(con) networkInteractor.getPlayer(id, this::onGetPlayerSuccess, this::onError )
        else{
            getPlayerFromDb(id)
        }
    }

    private fun getPlayerFromDb(id: Int) {
        Thread{
            Log.d("id", id.toString())
            val player = dbInteractor.getPlayer(id)
            Log.d("id", player.toString())
            onGetPlayerSuccess(player!!)
        }.start()
    }


    private fun onError(e: Throwable){
        screen?.showError(e)
    }

    private fun onGetPlayerSuccess(p: Player){
        Log.d("id", p.name)
        screen?.showPlayer(p)
    }

}