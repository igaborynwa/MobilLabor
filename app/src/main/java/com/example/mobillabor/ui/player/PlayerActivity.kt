package com.example.mobillabor.ui.player

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mobillabor.R
import com.example.mobillabor.databinding.ActivityPlayerBinding
import com.example.mobillabor.di.MainApplication
import com.example.mobillabor.model.Player
import javax.inject.Inject
import kotlin.properties.Delegates

class PlayerActivity : AppCompatActivity(), PlayerScreen {
    @Inject lateinit var playerPresenter: PlayerPresenter
    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as MainApplication).injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        playerPresenter.attachScreen(this)
        val playerId = intent.getIntExtra("PLAYER_ID", 0)
        playerPresenter.getPlayer(playerId, checkConnection())
    }

    override fun onStop() {
        super.onStop()
        playerPresenter.detachScreen()
    }

    override fun showError(e: Throwable?) {
        e!!.printStackTrace()
        Toast.makeText(applicationContext,  getString(R.string.networkErrorText), Toast.LENGTH_LONG).show()
    }

    override fun showPlayer(p: Player?) {
        title = p!!.name
        binding.player = p
    }


    private fun checkConnection(): Boolean{
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}