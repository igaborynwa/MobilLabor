package com.example.mobillabor.ui.player

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.StatsLog.logEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobillabor.R
import com.example.mobillabor.databinding.ActivityPlayerBinding
import com.example.mobillabor.di.MainApplication
import com.example.mobillabor.model.Player
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject


class PlayerActivity : AppCompatActivity(), PlayerScreen {
    @Inject
    lateinit var playerPresenter: PlayerPresenter
    private lateinit var binding: ActivityPlayerBinding

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
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
        this@PlayerActivity.runOnUiThread {
            e!!.printStackTrace()
            Toast.makeText(
                applicationContext,
                getString(R.string.networkErrorText),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun showPlayer(p: Player?) {
        this@PlayerActivity.runOnUiThread {
            title = p!!.name
            binding.player = p
        }
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, p!!.id.toString())
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, p.name)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

    }


    private fun checkConnection(): Boolean {
        val cm =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}