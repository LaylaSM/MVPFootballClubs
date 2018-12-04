package com.laylasm.mvpfootballclubs.view.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.laylasm.mvpfootballclubs.R

import com.laylasm.mvpfootballclubs.model.*
import com.laylasm.mvpfootballclubs.presenter.NextMatchPresenter
import com.laylasm.mvpfootballclubs.view.adapter.FootballClubAdapter
import kotlinx.android.synthetic.main.fragment_next_match.*

class NextMatchsFragment : Fragment (), MatchView.View{

    lateinit var mPresenter : NextMatchPresenter
    lateinit var clubName : String
    private var matchLists : MutableList <Data> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballMatchApi.getClient().create(FootballMatchEndPoint::class.java)
        val request = MatchRepoImp (service)
        val scheduler = AppSchedulerProvider()
        mPresenter = NextMatchPresenter(this, request, scheduler)
        mPresenter.getFootballMatchData()
        val spinnerClubItems = resources.getStringArray(R.array.club_teams)
        val spinnerClubAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerClubItems)
        spinnerNextMatch.adapter = spinnerClubAdapter
        spinnerNextMatch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                clubName = spinnerNextMatch.selectedItem.toString()
                when(clubName){
                    "English Premier League" -> mPresenter.getFootballMatchData("4328")
                    "German Bundesliga" -> mPresenter.getFootballMatchData("4331")
                    "Italian Serie A" -> mPresenter.getFootballMatchData("4332")
                    "French Ligue 1" -> mPresenter.getFootballMatchData("4334")
                    "Spanish La Liga" -> mPresenter.getFootballMatchData("4335")
                    "Netherlands Eredivisie" -> mPresenter.getFootballMatchData("4337")
                    else -> mPresenter.getFootballMatchData()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
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
        pbNextMatch.hide()
        rvFootballClub.visibility = View.VISIBLE
    }

    override fun showLoading() {
        pbNextMatch.hide()
        rvFootballClub.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }

}

