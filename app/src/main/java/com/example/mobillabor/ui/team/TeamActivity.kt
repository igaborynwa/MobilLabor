package com.example.mobillabor.ui.team

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobillabor.databinding.ActivityTeamBinding
import com.example.mobillabor.di.MainApplication
import com.example.mobillabor.model.Team
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
        teamPresenter.getTeam()

        initRecyclerView()
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

    override fun showTeam(team: Team) {

        title = team.name
        binding.team= team
        teamAdapter.setPlayers(team.squad!!)
        GlideToVectorYou.init().with(this).load(Uri.parse(team.crestUrl),binding.ivCrest)

    }

    override fun showNetworkError(e: Throwable) {
        e.printStackTrace()
        Toast.makeText(applicationContext, "Error during network communication!", Toast.LENGTH_LONG).show()
    }
}