package com.laylasm.mvpfootballclubs.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import com.laylasm.mvpfootballclubs.R
import com.laylasm.mvpfootballclubs.model.*
import com.laylasm.mvpfootballclubs.presenter.SearchMatchPresenter
import com.laylasm.mvpfootballclubs.view.adapter.FootballClubAdapter
import kotlinx.android.synthetic.main.activity_search_schedules.*

class SearchMatchActivity: AppCompatActivity(), SearchMatchView.View {

    private var matchLists : MutableList<Data> = mutableListOf()
    lateinit var mPresenter: SearchMatchView.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_schedules)

        val query = intent.getStringExtra("query")
        val service = FootballMatchApi.getClient().create(FootballMatchEndPoint::class.java)
        val request = MatchRepoImp(service)
        val scheduler = AppSchedulerProvider()
        mPresenter = SearchMatchPresenter(this, request, scheduler)
        mPresenter.searchMatch(query)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.item_menu_search)?.actionView as SearchView?
        searchView?.queryHint = "Search Schedules"
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mPresenter.searchMatch(newText)
                return false
            }
        })
        return true
    }

    override fun viewMatch(matchList: List<Data>) {
        matchLists.clear()
        matchLists.addAll(matchList)
        val layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvFootballClub.layoutManager = layoutManager
        rvFootballClub.adapter = FootballClubAdapter(matchList, applicationContext)
    }

    override fun showLoading() {
        pbSearchMatch.show()
        rvFootballClub.hide()
    }

    override fun hideLoading() {
        pbSearchMatch.hide()
        rvFootballClub.show()
    }

}
