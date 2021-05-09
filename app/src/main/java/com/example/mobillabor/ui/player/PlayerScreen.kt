package com.example.mobillabor.ui.player

import com.example.mobillabor.model.Player

interface PlayerScreen {
    fun showError(e: Throwable?)
    fun showPlayer(p: Player?)
}