package com.laylasm.mvpfootballclubs.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.*
import com.laylasm.mvpfootballclubs.presenter.FavMatchPresenter
import com.laylasm.mvpfootballclubs.view.adapter.FootballClubAdapter
import kotlinx.android.synthetic.main.fragment_favorite_match.*

class FavMatchFragment  : Fragment(), FavMatchView.View {
    private var matchLists : MutableList<Data> = mutableListOf()
    lateinit var mPresenter : FavMatchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballMatchApi.getClient().create(FootballMatchEndPoint::class.java)
        val request = MatchRepoImp(service)
        val localRepositoryImpl = LocalRepoImp(context as Context)
        val scheduler = AppSchedulerProvider()
        mPresenter = FavMatchPresenter(this, request, localRepositoryImpl, scheduler)
        mPresenter.getFootballMatchData()

        srlSwipeRefreshLayoutFav.setOnRefreshListener {
            mPresenter.getFootballMatchData()
        }

    }

    override fun displayFootballMatch(matchList: List<Data>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvFootballClub.layoutManager = layoutManager
        rvFootballClub.adapter = FootballClubAdapter(matchList, context)
    }

    override fun hideLoading() {
        pbFavMatch.hide()
        rvFootballClub.visibility = View.VISIBLE
    }

    override fun showLoading() {
        pbFavMatch.show()
        rvFootballClub.visibility = View.INVISIBLE
    }

    override fun hideSwipeRefresh() {
        srlSwipeRefreshLayoutFav.isRefreshing = false
        pbFavMatch.hide()
        rvFootballClub.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }


}
