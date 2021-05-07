package com.example.mobillabor.ui.player

import com.example.mobillabor.interactor.DBInteractor
import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.model.Player
import com.example.mobillabor.ui.Presenter
import javax.inject.Inject

class PlayerPresenter @Inject constructor(private val networkInteractor: NetworkInteractor, private val dbInteractor: DBInteractor): Presenter<PlayerScreen?>() {

    fun getPlayer(id: Int, con: Boolean){
        if(con)
            Thread{networkInteractor.getPlayer(id, this::onGetPlayerSuccess, this::onError)}.start()
        else{
            getPlayerFromDb(id)
        }
    }

    private fun getPlayerFromDb(id: Int) {
        Thread{
            val player = dbInteractor.getPlayer(id)
            onGetPlayerSuccess(player!!)
        }.start()
    }


    private fun onError(e: Throwable){
        screen?.showError(e)
    }

    private fun onGetPlayerSuccess(p: Player){
        screen?.showPlayer(p)
    }

}