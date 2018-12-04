package com.laylasm.mvpfootballclubs.view.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.*
import com.laylasm.mvpfootballclubs.presenter.TeamClubPresenter
import com.laylasm.mvpfootballclubs.view.adapter.TeamClubAdapter
import kotlinx.android.synthetic.main.fragment_teams_club.*

class TeamsClubFragment : Fragment(), TeamClubView.View {

    lateinit var clubsName: String
    lateinit var mPresenter: TeamClubView.Presenter
    private var clubLists: MutableList<TeamData> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val service = FootballMatchApi.getClient().create(FootballMatchEndPoint::class.java)
        val request = TeamMatchRepoImp(service)
        val scheduler = AppSchedulerProvider()
        setHasOptionsMenu(true)
        mPresenter = TeamClubPresenter(this, request, scheduler)
        mPresenter.getTeamData("4328")
        val spinnerClubItems = resources.getStringArray(R.array.club_teams)
        val spinnerClubAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerClubItems)
        spinnerTeamClub.adapter = spinnerClubAdapter
        spinnerTeamClub.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                clubsName = spinnerTeamClub.selectedItem.toString()
                when(clubsName){
                    "English Premier League" -> mPresenter.getTeamData("4328")
                    "German Bundesliga" -> mPresenter.getTeamData("4331")
                    "Italian Serie A" -> mPresenter.getTeamData("4332")
                    "French Ligue 1" -> mPresenter.getTeamData("4334")
                    "Spanish La Liga" -> mPresenter.getTeamData("4335")
                    "Netherlands Eredivisie" -> mPresenter.getTeamData("4337")
                    else -> mPresenter.getTeamData("4328")
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_teams_club, container, false)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.item_menu_search)?.actionView as SearchView?
        searchView?.queryHint = "Cari club apa?"

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mPresenter.searchTeam(newText)
                return false
            }
        })

        searchView?.setOnCloseListener(object: SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                mPresenter.getTeamData("4328")
                return true
            }
        })
    }

    override fun displayTeams(teamList: List<TeamData>) {
        clubLists.clear()
        clubLists.addAll(teamList)
        val layoutManager = GridLayoutManager(context, 4)
        rvTeamClub.layoutManager = layoutManager
        rvTeamClub.adapter = TeamClubAdapter(clubLists, context)

    }

    override fun hideLoading() {
        pbTeamsClub.hide()
        rvTeamClub.visibility = View.VISIBLE
    }

    override fun showLoading() {
        pbTeamsClub.show()
        rvTeamClub.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter.onDestroy()
    }

}



