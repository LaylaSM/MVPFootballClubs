package com.laylasm.mvpfootballclubs.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.*
import com.laylasm.mvpfootballclubs.presenter.FavTeamPresenter
import com.laylasm.mvpfootballclubs.view.adapter.TeamClubAdapter
import kotlinx.android.synthetic.main.fragment_fav_team.*

class FavTeamFragment : Fragment(), FavTeamView.View {

    private var clubLists : MutableList<TeamData> = mutableListOf()
    lateinit var mPresenter : FavTeamPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fav_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sercive = FootballMatchApi.getClient().create(FootballMatchEndPoint::class.java)
        val localRepositoryImpl = LocalRepoImp(context as Context)
        val teamRepositoryImpl = TeamMatchRepoImp(sercive)
        val scheduler = AppSchedulerProvider()
        mPresenter = FavTeamPresenter(this, localRepositoryImpl, teamRepositoryImpl, scheduler)
        mPresenter.getTeamData()

        swiperefresh.setOnRefreshListener {
            mPresenter.getTeamData()
        }
    }

    override fun displayTeams(teamList: List<TeamData>) {
        clubLists.clear()
        clubLists.addAll(teamList)
        val layoutManager = GridLayoutManager(context, 4)
        rvTeamClub.layoutManager = layoutManager
        rvTeamClub.adapter = TeamClubAdapter(clubLists, context)
    }

    override fun hideLoading() {
        pbFavTeamMatch.hide()
        rvTeamClub.visibility = View.VISIBLE
    }

    override fun showLoading() {
        pbFavTeamMatch.show()
        rvTeamClub.visibility = View.GONE
    }

    override fun hideSwipeRefresh() {
        swiperefresh.isRefreshing = false
        pbFavTeamMatch.hide()
        rvTeamClub.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }


}
