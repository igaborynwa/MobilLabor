package com.example.mobillabor.ui.player

import com.example.mobillabor.interactor.NetworkInteractor
import com.example.mobillabor.model.Player
import com.example.mobillabor.ui.Presenter
import javax.inject.Inject

class PlayerPresenter @Inject constructor(private val networkInteractor: NetworkInteractor): Presenter<PlayerScreen?>() {

    fun getPlayer(id: Int){
        networkInteractor.getPlayer(id, this::onGetPlayerSuccess, this::onError )
    }

    private fun onError(e: Throwable){
        screen?.showError(e)
    }

    private fun onGetPlayerSuccess(p: Player){
        screen?.showPlayer(p)
    }
}