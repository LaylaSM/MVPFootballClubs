package com.laylasm.mvpfootballclubs.model

interface FavTeamView {

    interface View{
        fun displayTeams(teamList: List<TeamData>)
        fun hideLoading()
        fun showLoading()
        fun hideSwipeRefresh()
    }

    interface Presenter{
        fun getTeamData()
        fun onDestroy()
    }
}