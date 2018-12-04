package com.laylasm.mvpfootballclubs.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.PlayerMatch
import com.laylasm.mvpfootballclubs.view.activity.PlayerMatchDetailActivity
import kotlinx.android.synthetic.main.item_players.view.*
import org.jetbrains.anko.startActivity

class PlayerClubAdapter (val listPlayer:List<PlayerMatch>, val context: Context?):
        RecyclerView.Adapter<PlayerClubAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_players, parent, false))
    }

    override fun getItemCount()= listPlayer.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(listPlayer[position])
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(player: PlayerMatch){
            itemView.tvPlayer.text = player.strPlayer
            Glide.with(itemView.context)
                    .load(player.strCutout)
                    .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                    .apply(RequestOptions().override(120, 140))
                    .into(itemView.ivPlayerDetails)

            itemView.setOnClickListener {
                itemView.context.startActivity<PlayerMatchDetailActivity>("player" to player)
            }
        }

    }

}