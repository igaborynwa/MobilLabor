package com.example.mobillabor.ui.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mobillabor.R
import com.example.mobillabor.databinding.ActivityPlayerBinding
import com.example.mobillabor.di.MainApplication
import com.example.mobillabor.model.Player
import javax.inject.Inject

class PlayerActivity : AppCompatActivity(), PlayerScreen {
    @Inject lateinit var playerPresenter: PlayerPresenter
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as MainApplication).injector.inject(this)
        val playerId = intent.getIntExtra("PLAYER_ID", 0)
        playerPresenter.getPlayer(playerId)

    }

    override fun onStart() {
        super.onStart()
        playerPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        playerPresenter.detachScreen()
    }

    override fun showError(e: Throwable) {
        e.printStackTrace()
        Toast.makeText(applicationContext, "Error during network communication!", Toast.LENGTH_LONG).show()
    }

    override fun showPlayer(player: Player) {
        title = player.name
        binding.player = player
    }

    override fun showPlayerModified(p: Player) {
        title=p.name
        binding.player=p
    }

    fun modifyPlayer(player: Player){
        playerPresenter.modifyPlayer(player)
    }
}