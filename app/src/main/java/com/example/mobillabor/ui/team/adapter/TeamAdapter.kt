package com.example.mobillabor.ui.team.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobillabor.R
import com.example.mobillabor.databinding.PlayerRowBinding
import com.example.mobillabor.model.Player
import com.example.mobillabor.ui.player.PlayerActivity
import com.example.mobillabor.ui.team.TeamActivity

class TeamAdapter(private var activity: TeamActivity) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    var playerList = mutableListOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(activity)
        val binding: PlayerRowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.player_row, parent, false)
        binding.adapter = this
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = playerList[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun setPlayers(players: List<Player>) {
        this.playerList.clear()
        this.playerList.addAll(players)
        notifyDataSetChanged()
    }

    fun showPlayer(player: Player) {
        val intent = Intent(activity, PlayerActivity::class.java)
        intent.putExtra("PLAYER_ID", player.id)
        activity.startActivity(intent)
    }

    fun deletePlayer(player: Player) {
        val pos = playerList.indexOf(player)
        playerList.remove(player)
        notifyItemRemoved(pos)
        activity.deletePlayer(player)
    }

    fun addFav(player: Player) {
        activity.addPlayer(player)
    }

    fun modify(player: Player){
        activity.modify(player)
    }

    class ViewHolder(private val binding: PlayerRowBinding) : RecyclerView.ViewHolder(binding.root) {
        private var fav = false
        fun bind(player: Player) {
            binding.player = player
            binding.btnFav.setOnClickListener {
                if (fav) {
                    binding.btnFav.setImageResource(android.R.drawable.star_big_off)
                    binding.adapter?.modify(player)
                } else {
                    binding.btnFav.setImageResource(android.R.drawable.star_big_on)
                    binding.adapter?.addFav(player)
                }
                fav=!fav

            }
        }

    }
}