package com.laylasm.mvpfootballclubs.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.TeamData
import kotlinx.android.synthetic.main.fragment_team_desc.*

class TeamDescFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_desc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val team: TeamData? = arguments?.getParcelable("teams")
        initView(team)
    }

    fun initView(teamInfo: TeamData?){
        Glide.with(this)
                .load(teamInfo?.strTeamBadge)
                .apply(RequestOptions().placeholder(R.drawable.ic_add_fav_24dp))
                .into(ivTeamDesc)

        tvTeamNameDesc.text = teamInfo?.strTeam
        tvManagerNameDesc.text = teamInfo?.strManager
        tvPlaceNameDesc.text = teamInfo?.strStadium
        tvTeamDesc.text = teamInfo?.strDescriptionEN
    }


}
