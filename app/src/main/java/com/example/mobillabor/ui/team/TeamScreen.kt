package com.example.mobillabor.ui.team

import com.example.mobillabor.model.Team

interface TeamScreen {
    fun showTeam(team: Team)
    fun showNetworkError(e: Throwable)
    fun showDeleteSuccess(playerName: String)
    fun showPlayerAdded(id: Int)
}