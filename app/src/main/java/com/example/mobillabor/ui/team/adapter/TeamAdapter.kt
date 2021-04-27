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

class TeamAdapter(private var context: Context): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    var playerList = mutableListOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: PlayerRowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.player_row, parent, false)
        binding.adapter=this
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player =playerList[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun setPlayers(players: List<Player>){
        this.playerList.clear()
        this.playerList.addAll(players)
        notifyDataSetChanged()
    }

    fun showPlayer(player: Player){
        val intent = Intent(context, PlayerActivity::class.java)
        intent.putExtra("PLAYER_ID", player.id)
        context.startActivity(intent)
    }

    class ViewHolder(private val binding: PlayerRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player){
            binding.player = player
        }

    }
}