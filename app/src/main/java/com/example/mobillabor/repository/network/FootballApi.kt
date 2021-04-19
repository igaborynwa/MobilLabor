package com.example.mobillabor.repository.network

import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team
import retrofit2.Call
import retrofit2.http.*


interface FootballApi {
    companion object{
        const val ENDPOINT_URL="https://api.football-data.org/v2/"
    }

    @GET("teams/{id}")
    fun getTeamById(@Path("id") id: Int, @Header("X-Auth-Token") xAuthToken: String): Call<Team>

    /**
     *
     * Returns the player with the given id.
     * @param id id of player
     * @param xAuthToken Authentication token
     * @return Call<Void>
    </Void> */
    @GET("players/{id}")
    fun getPlayersId(@Path("id") id: Int, @Header("X-Auth-Token") xAuthToken: String): Call<Player>


    /**
     *
     * Update player
     * @param id id of player
     * @param xAuthToken Authentication token
     * @return Call<Void>
    </Void> */
    @PUT("players/{id}")
    fun putPlayersId(@Path("id") id: Int, @Header("X-Auth-Token") xAuthToken: String, @Body param: Player): Call<Player>


    /**
     *
     * Mark player as favourite
     * @param xAuthToken Authentication token
     * @return Call<Void>
    </Void> */
    @POST("players")
    fun postPlayersId( @Header("X-Auth-Token") xAuthToken: String, @Body param: Player): Call<Int>


    /**
     *
     * Delete player
     * @param id id of player
     * @param xAuthToken Authentication token
     * @return Call<Void>
    </Void> */
    @DELETE("players/{id}")
    fun deletePlayersId(@Path("id") id: Int, @Header("X-Auth-Token") xAuthToken: String): Call<Void>

}