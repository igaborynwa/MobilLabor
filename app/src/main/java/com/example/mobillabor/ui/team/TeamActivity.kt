package com.example.mobillabor.ui.team

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobillabor.R
import com.example.mobillabor.databinding.ActivityTeamBinding
import com.example.mobillabor.di.MainApplication
import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import com.example.mobillabor.ui.about.AboutActivity
import com.example.mobillabor.ui.team.adapter.TeamAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import javax.inject.Inject


class TeamActivity : AppCompatActivity(), TeamScreen {
    @Inject
    lateinit var teamPresenter: TeamPresenter
    private lateinit var binding: ActivityTeamBinding
    lateinit var teamAdapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as MainApplication).injector.inject(this)
        teamPresenter.getTeam(checkConnection())
        initRecyclerView()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        startActivity(Intent(applicationContext, AboutActivity::class.java))
        return true
    }



    private fun checkConnection(): Boolean{
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun initRecyclerView(){
        teamAdapter = TeamAdapter(this)
        binding.playerList.adapter = teamAdapter
    }

    override fun onStart() {
        super.onStart()
        teamPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        teamPresenter.detachScreen()
    }

    override fun showTeam(team: Team?) {
        this@TeamActivity.runOnUiThread {
        title = team!!.name
        binding.team= team
        teamAdapter.setPlayers(team.squad)
        GlideToVectorYou.init().with(this).load(Uri.parse(team.crestUrl), binding.ivCrest)
        }
    }

    override fun showNetworkError(e: Throwable) {
        e.printStackTrace()
        Toast.makeText(
            applicationContext,
            getString(R.string.networkErrorText),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showDeleteSuccess(playerName: String?) {
        Toast.makeText(applicationContext, "$playerName sikeresen törölve lett!", Toast.LENGTH_SHORT).show()
    }

    override fun showPlayerAdded(id: Int?) {
        Toast.makeText(applicationContext, getString(R.string.playerAddedText), Toast.LENGTH_SHORT).show()
    }

    override fun showPlayerModified(player: Player?) {
        Toast.makeText(applicationContext, getString(R.string.playerModifiedText), Toast.LENGTH_SHORT).show()
    }

    fun deletePlayer(player: Player){
        teamPresenter.deletePlayer(player)
    }

    fun addPlayer(player: Player){
        teamPresenter.addPlayer(player)
    }

    fun modify(player: Player){
        teamPresenter.modifyPlayer(player)
    }
}