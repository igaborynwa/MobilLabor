package com.example.mobillabor.ui.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobillabor.databinding.ActivityTeamBinding
import com.example.mobillabor.di.MainApplication
import com.example.mobillabor.model.Team
import javax.inject.Inject

class TeamActivity : AppCompatActivity(), TeamScreen {
    @Inject
    lateinit var teamPresenter: TeamPresenter
    private lateinit var binding: ActivityTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as MainApplication).injector.inject(this)
        teamPresenter.getTeam()

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
        binding.tvTeamName.text= team.name
    }

    override fun showNetworkError(e: Throwable) {
        e.printStackTrace()
    }
}