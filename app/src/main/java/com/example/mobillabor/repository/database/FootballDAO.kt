package com.example.mobillabor.repository.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team

@Dao
interface FootballDAO {
    @Query("SELECT * FROM teams")
    fun getTeams(): List<Team>

    @Query("SELECT * FROM teams WHERE id = :id")
    fun getTeam(id: Int): Team?

    @Query("SELECT * FROM players")
    fun getPlayers(): List<Player>

    @Query("SELECT * FROM players WHERE id = :id")
    fun getPlayer(id: Int): Player?

    @Insert
    fun insertTeam(team: Team)

    @Insert
    fun insertPlayer(player: Player)

    @Delete
    fun deletePlayer(player: Player)

    @Delete
    fun deleteTeam(team: Team)

}