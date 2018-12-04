package com.laylasm.mvpfootballclubs.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.*
import com.laylasm.mvpfootballclubs.presenter.PlayerMatchPresenter
import com.laylasm.mvpfootballclubs.view.adapter.PlayerClubAdapter
import kotlinx.android.synthetic.main.fragment_players_match.*

class PlayerMatchFragment : Fragment(), PlayerMatchView.View {

    private var listPlayer : MutableList<PlayerMatch> = mutableListOf()
    lateinit var mPresenter: PlayerMatchView.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_players_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val team: TeamData? = arguments?.getParcelable("teams")
        val service = FootballMatchApi.getClient().create(FootballMatchEndPoint::class.java)
        val request = PlayerMatchRepoImp(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = PlayerMatchPresenter(this, request, scheduler)
        mPresenter.getAllPlayer(team?.idTeam)

    }

    override fun displayPlayers(playerList: List<PlayerMatch>) {
        listPlayer.clear()
        listPlayer.addAll(playerList)
        val layoutManager = GridLayoutManager(context, 4)
        rvFootballClub.layoutManager = layoutManager
        rvFootballClub.adapter = PlayerClubAdapter(listPlayer, context)
    }

    override fun showLoading() {
        pbPlayersMatch.show()
        rvFootballClub.visibility = View.GONE
    }

    override fun hideLoading() {
        pbPlayersMatch.hide()
        rvFootballClub.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
    }


}
