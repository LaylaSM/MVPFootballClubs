package com.laylasm.mvpfootballclubs.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.TeamData
import com.laylasm.mvpfootballclubs.view.activity.TeamDetailActivity
import kotlinx.android.synthetic.main.item_teams.view.*
import org.jetbrains.anko.startActivity

class TeamClubAdapter(val teamList: List<TeamData>, val context: Context?): RecyclerView.Adapter<TeamClubAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teams, parent, false))
    }

    override fun getItemCount() = teamList.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teamList[position])
    }


    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(team: TeamData){
            itemView.tvItemTeams.text = team.strTeam
            Glide.with(itemView.context)
                    .load(team.strTeamBadge)
                    .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                    .into(itemView.ivItemTeams)

            itemView.setOnClickListener {
                itemView.context.startActivity<TeamDetailActivity>("teamData" to team)
            }
        }

    }
}