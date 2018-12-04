package com.laylasm.mvpfootballclubs.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.Data
import com.laylasm.mvpfootballclubs.model.DateHelper
import com.laylasm.mvpfootballclubs.view.activity.DetailsActivity
import kotlinx.android.synthetic.main.abc_activity_chooser_view.view.*
import kotlinx.android.synthetic.main.item_main_match.view.*
import org.jetbrains.anko.startActivity

class FootballClubAdapter (val eventList: List<Data>, val context: Context?) : RecyclerView.Adapter<FootballClubAdapter.ClubViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        return ClubViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_match, parent, false))
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    inner class ClubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Data) {
            if (event.intHomeScore == null) {
                itemView.tvDateSchedule.setTextColor(itemView.context.resources.getColor(R.color.colorPrimary))
            }
            itemView.tvDateSchedule.text = event.dateEvent?.let { DateHelper.formatDateToMatch(it) }
            itemView.tvHomeName.text = event.strHomeTeam
            itemView.tvHomeScore.text = event.intHomeScore
            itemView.tvAwayName.text = event.strAwayTeam
            itemView.tvAwayScore.text = event.intAwayScore

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailsActivity>("match" to event)
            }
        }
    }



}